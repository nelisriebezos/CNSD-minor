import json
import boto3
from boto3.dynamodb.conditions import Key
from aws_xray_sdk.core import patch_all

patch_all()

def lambda_handler(event, context):
    print (f"{event} {type(event)}")

    user_id = event['pathParameters']['user_id']
    note_id = event['pathParameters']['note_id']

    body = json.loads(event["body"])

    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('notes-2.3.2')

    response = table.update_item(
        Key={
            'ID': f'USER#{user_id}',
            'SK': f'NOTE#{note_id}'
        },
        UpdateExpression="set tekst = :t",
        ExpressionAttributeValues={
            ':t': body["tekst"]
        },
        ReturnValues="UPDATED_NEW"
    )

    return {
        'statusCode': 200,
        'body': 'Note updated',
        'headers': {
            'content-type': 'application/json'
        },
        "isBase64Encoded": False
    }