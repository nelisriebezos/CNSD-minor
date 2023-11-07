import json
import boto3
import uuid
from aws_xray_sdk.core import patch_all

patch_all()

def lambda_handler(event, context):

    print (f"{event} {type(event)}")

    user_id = event['pathParameters']['user_id']
    body = json.loads(event["body"])

    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('notes-2.3.2')

    item={
        'ID': f'USER#{user_id}',
        'SK': f'NOTE#{str(uuid.uuid4())}',
        'tekst': body["tekst"],
        'Tag': body["tag"],
        'Type': 'NOTE',
        'GSI1PK': f'TAG#{body["tag"]}',
        'GSI1SK': f'USER#{user_id}'
    }

    response = table.put_item(Item=item)
    print (f"{response} {type(response)}")

    return {
        "statusCode": 200,
        "body": json.dumps(item)
    }