package com.example.awskcl3springboot.kinesis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.processor.ShardRecordProcessorFactory;

@Component
public class SampleRecordProcessorFactory implements ShardRecordProcessorFactory {

    private ApplicationContext context;

    @Override
    public ShardRecordProcessor shardRecordProcessor() {
        return new SampleRecordProcessor();
    }

    private ShardRecordProcessor getShardRecordProcessor() {
        return context.getBean(ShardRecordProcessor.class);
    }

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }
}
