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

    response = client.initiate_auth(
        ClientId='43j5pralhj2l8bdnlo0fvjkk56',
        AuthFlow='USER_PASSWORD_AUTH',
        AuthParameters={
            "USERNAME": body['email'],
            "PASSWORD": body['password']
        }
    )

    return {
        "statusCode": 200,
        "body": json.dumps(response),
    }
