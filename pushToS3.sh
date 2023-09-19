#!/bin/bash

S3_BUCKET="cnsd-first-bucket"

echo "Copy content to the S3 bucket ${S3_BUCKET}:"
aws s3 cp index.html s3://${S3_BUCKET}/index.html

echo "Content of the S3 bucket ${S3_BUCKET}:"
aws s3 ls $S3_BUCKET
