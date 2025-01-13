package com.example.awskcl3springboot.kinesis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.KinesisClientUtil;
import software.amazon.kinesis.coordinator.Scheduler;

import java.util.UUID;

@Configuration
public class SampleKinesisReaderConfig {

    @Autowired
    private SampleRecordProcessorFactory sampleRecordProcessorFactory;

    @Bean
    public Scheduler scheduler() {
        ConfigsBuilder configsBuilder = configsBuilder();
        return new Scheduler(
                configsBuilder.checkpointConfig(),
                configsBuilder.coordinatorConfig(),
                configsBuilder.leaseManagementConfig(),
                configsBuilder.lifecycleConfig(),
                configsBuilder.metricsConfig(),
                configsBuilder.processorConfig(),
                configsBuilder.retrievalConfig()
        );
    }

    private ConfigsBuilder configsBuilder() {
        return new ConfigsBuilder(
                "kinesis-consumer-stream-name",
                "kinesis-consumer-application",
                kinesisAsyncClient(),
                dynamoDbAsyncClient(),
                cloudWatchAsyncClient(),
                UUID.randomUUID().toString(),
                sampleRecordProcessorFactory
                ).tableName("kinesis-consumer-table");
    }

    private KinesisAsyncClient kinesisAsyncClient() {
        return KinesisClientUtil.createKinesisAsyncClient(KinesisAsyncClient.builder().region(Region.AP_NORTHEAST_1));
    }

    private CloudWatchAsyncClient cloudWatchAsyncClient() {
        return CloudWatchAsyncClient.builder().region(Region.AP_NORTHEAST_1).credentialsProvider(DefaultCredentialsProvider.create()).build();
    }

    private DynamoDbAsyncClient dynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder().region(Region.AP_NORTHEAST_1).credentialsProvider(DefaultCredentialsProvider.create()).build();
    }

}
