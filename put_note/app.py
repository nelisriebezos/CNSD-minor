import boto3
import json
import decimal, datetime

from aws_xray_sdk.core import patch_all
from sqlalchemy import text, create_engine, update
from datetime import date

patch_all()

ssm = boto3.client('ssm', region_name='us-east-1')
username = ssm.get_parameter(Name="/dev/postgres/username")['Parameter']['Value']
password = ssm.get_parameter(Name="/dev/postgres/password")['Parameter']['Value']
database_url = ssm.get_parameter(Name="/dev/postgres/database_url")['Parameter']['Value']
port = ssm.get_parameter(Name="/dev/postgres/port")['Parameter']['Value']
database = ssm.get_parameter(Name="/dev/postgres/database")['Parameter']['Value']

connection_string = f"postgresql://{username}:{password}@{database_url}:{port}/{database}"

engine = create_engine(connection_string)


def alchemyencoder(obj):
    """JSON encoder function for SQLAlchemy special classes."""
    if isinstance(obj, datetime.date):
        return obj.isoformat()
    elif isinstance(obj, decimal.Decimal):
        return float(obj)


def lambda_handler(event, context):
    print("Event:")
    print(f"{event} {type(event)}")

    id = event['pathParameters']['id']
    tekst = json.loads(event.get('body')).get('tekst')

    with engine.connect() as connection:
        query = text("UPDATE public.notes SET tekst=:x WHERE id=:y")
        query = query.bindparams(x=tekst, y=id)
        result = connection.execute(query)

    return {
        'statusCode': 200,
        'body': None,
        'headers': {
            'content-type': 'application/json'
        },
        "isBase64Encoded": False
    }