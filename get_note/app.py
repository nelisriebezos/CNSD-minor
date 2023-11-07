import json
import boto3
import decimal, datetime

from aws_xray_sdk.core import patch_all
from sqlalchemy import text, create_engine

patch_all()

ssm = boto3.client('ssm')
username = ssm.get_parameter(Name="/dev/postgres/username")['Parameter']['Value']
password = ssm.get_parameter(Name="/dev/postgres/password")['Parameter']['Value']
database_url = ssm.get_parameter(Name="/dev/postgres/database_url")['Parameter']['Value']
port = ssm.get_parameter(Name="/dev/postgres/port")['Parameter']['Value']
database = ssm.get_parameter(Name="/dev/postgres/database")['Parameter']['Value']

database_url = f"postgresql://{username}:{password}@{database_url}:{port}/{database}"

engine = create_engine(database_url)

def alchemyencoder(obj):
    """JSON encoder function for SQLAlchemy special classes."""
    if isinstance(obj, datetime.date):
        return obj.isoformat()
    elif isinstance(obj, decimal.Decimal):
        return float(obj)

def lambda_handler(event, context):
    print (f"{event} {type(event)}")

    id = event['pathParameters']['id']

    with engine.connect() as connection:
        query = text("SELECT * FROM public.notes WHERE id=:x")
        query = query.bindparams(x=id)
        result = connection.execute(query)

    notes = json.dumps([dict(row) for row in result], default=alchemyencoder)

    if notes == '[]':
        return {
            'statusCode': 404,
            'body': notes,
            'headers': {
                'content-type': 'application/json'
            },
            "isBase64Encoded": False
        }

    return {
        'statusCode': 200,
        'body': notes,
        'headers': {
            'content-type' : 'application/json'
        },
        "isBase64Encoded": False
    }
