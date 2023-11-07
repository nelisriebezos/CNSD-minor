import boto3
import json
import decimal, datetime

from aws_xray_sdk.core import patch_all
from sqlalchemy import text, create_engine
from datetime import date

patch_all()

ssm = boto3.client('ssm')
username = ssm.get_parameter(Name="/dev/postgres/username")['Parameter']['Value']
password = ssm.get_parameter(Name="/dev/postgres/password")['Parameter']['Value']
database_url = ssm.get_parameter(Name="/dev/postgres/database_url")['Parameter']['Value']
port = ssm.get_parameter(Name="/dev/postgres/port")['Parameter']['Value']
database = ssm.get_parameter(Name="/dev/postgres/database")['Parameter']['Value']

database_url = f"postgresql://{username}:{password}@{database_url}:{port}/{database}"

engine = create_engine(database_url)

def lambda_handler(event, context):

    print (f"{event} {type(event)}")

    id = event['pathParameters']['id']

    with engine.connect() as connection:
        query = text("DELETE FROM public.notes WHERE id=:x")
        query = query.bindparams(x=id)
        connection.execute(query)

    return {
        'statusCode': 202,
        'body': None,
        'headers': {
            'content-type' : 'application/json'
        },
        "isBase64Encoded": False
    }
