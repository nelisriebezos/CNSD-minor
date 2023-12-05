import boto3
import json

# Create a Cognito client
client = boto3.client('cognito-idp')

def lambda_handler(event, context):
    # Get the token from the event
    access_token = event['headers']['Authorization']

    # Check if the user is logged in
    try:
        response = client.get_user(
            AccessToken=access_token
        )
        return {
            'statusCode': 200,
            'body': json.dumps(response)
        }
    except:
        return {
            'statusCode': 401,
            'body': json.dumps('Unauthorized')
        }


