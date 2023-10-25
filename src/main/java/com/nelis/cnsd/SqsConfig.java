package com.nelis.cnsd;

import com.amazon.sqs.javamessaging.AmazonSQSExtendedClient;
import com.amazon.sqs.javamessaging.ExtendedClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqsConfig {
    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${cloud.aws.credentials.session-token}")
    private String awsSessionToken;

    @Value("${cloud.aws.sqs.s3-bucket-name}")
    private String s3BucketName;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(final AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicSessionCredentials(awsAccessKey, awsSecretKey, awsSessionToken)))
                .build();
    }

    @Bean("s3Client")
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(awsAccessKey, awsSecretKey, awsSessionToken))
                ).build();
    }

    @Bean
    public ExtendedClientConfiguration ExtendedClientConfiguration(AmazonS3 s3Client) {
        return new ExtendedClientConfiguration()
                .withLargePayloadSupportEnabled(s3Client, s3BucketName);
    }

    @Bean
    public AmazonSQSExtendedClient amazonSQSExtendedClient(ExtendedClientConfiguration extendedClientConfig) {
        return new AmazonSQSExtendedClient(AmazonSQSClientBuilder
                .standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(awsAccessKey, awsSecretKey, awsSessionToken))
                ).withRegion(region).build(), extendedClientConfig);
    }
}
