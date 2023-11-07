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

    tekst = json.loads(event.get('body')).get('tekst')
    aangemaakt = date.today().strftime("%d/%m/%Y")

    with engine.connect() as connection:
        query = text("INSERT INTO public.notes (tekst, aangemaakt) VALUES (:x, :y)")
        query = query.bindparams(x=tekst, y=aangemaakt)
        connection.execute(query)

    return {
        'statusCode': 201,
        'body': None,
        'headers': {
            'content-type' : 'application/json'
        },
        "isBase64Encoded": False
    }
