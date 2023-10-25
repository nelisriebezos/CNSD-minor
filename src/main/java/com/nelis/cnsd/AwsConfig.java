package com.nelis.cnsd;

import com.amazon.sqs.javamessaging.AmazonSQSExtendedClient;
import com.amazon.sqs.javamessaging.ExtendedClientConfiguration;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class AwsConfig {
    @Value("${cloud.aws.sqs.s3-bucket-name}")
    private String s3BucketName;

    @Bean("extendedClientConfig")
    public ExtendedClientConfiguration extendedClientConfiguration(final AmazonS3 s3Client) {
        return new ExtendedClientConfiguration()
                .withLargePayloadSupportEnabled(s3Client, s3BucketName);
    }

    @Bean("sqsConnectionFactory")
    public ConnectionFactory sqsConnectionFactory(final AmazonSQSExtendedClient amazonSQSExtendedClient) {
        final ProviderConfiguration providerConfiguration = new ProviderConfiguration();
        providerConfiguration.setNumberOfMessagesToPrefetch(10);

        final SQSConnectionFactory sqsConnectionFactory = new SQSConnectionFactory(providerConfiguration,
                amazonSQSExtendedClient);

        return sqsConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory sqsConnectionFactory) {
        final DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(sqsConnectionFactory);

        return jmsListenerContainerFactory;
    }
}
