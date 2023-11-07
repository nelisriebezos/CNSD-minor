import json
import boto3
from boto3.dynamodb.conditions import Key
from aws_xray_sdk.core import patch_all

patch_all()

def lambda_handler(event, context):

    print (f"{event} {type(event)}")
    user_id = event['pathParameters']['user_id']
    note_id = event['pathParameters']['note_id']

    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('notes-2.3.2')

    response = table.get_item(Key={
        'ID': f'USER#{user_id}',
        'SK': f'NOTE#{note_id}'
    })

    print(f"{response}")

    if 'Item' in response:
        return {
            'statusCode': 200,
            'body': json.dumps(response['Item']),
            'headers': {
                'content-type': 'application/json'
            },
            "isBase64Encoded": False
        }

    return {
        'statusCode': 404,
        'body': 'Not Found',
        'headers': {
            'content-type': 'application/json'
        },
        "isBase64Encoded": False
    }

