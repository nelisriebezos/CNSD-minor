import json
import boto3
import hmac, hashlib, base64
from aws_xray_sdk.core import patch_all

patch_all()

app_client_id = "Client ID"

def lambda_handler(event, context):
    print(f"{event} {type(event)}")

    body = json.loads(event["body"])
    client = boto3.client("cognito-idp")

    response = client.sign_up(
        ClientId='43j5pralhj2l8bdnlo0fvjkk56',
        Username=body['email'],
        Password=body['password'],
        UserAttributes=[
            {
                'Name': 'email',
                'Value': body['email']
            }
        ]
    )

    return {
        "statusCode": 200,
        "body": json.dumps(response)
    }
