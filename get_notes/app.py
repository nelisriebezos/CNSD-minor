import json
import boto3
from boto3.dynamodb.conditions import Key, Attr
from aws_xray_sdk.core import patch_all

patch_all()

def lambda_handler(event, context):
    print (f"{event} {type(event)}")
    user_id = event['pathParameters']['user_id']

    dynamodb = boto3.resource('dynamodb', region_name="us-east-1")
    table = dynamodb.Table('notes-2.3.2')
    notes = []
    done = False
    start_key = None

    while not done:
        response = table.scan(FilterExpression=Key('ID').begins_with(f"USER#{user_id}") & Attr('Type').eq('NOTE'))
        print(f"{response}")
        notes += response['Items']
        start_key = response.get('LastEvaluatedKey', None)
        done = start_key is None

    return {
        "statusCode": 200,
        "body": json.dumps(notes)
    }