package com.ch.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author ChenHao
 * @date 2023/9/3 17:48
 */

public class KafkaProducerTest {
    public static void main(String[] args) {
        // Kafka 服务器地址和端口
        String bootstrapServers = "123.60.166.121:9092";
        // 主题名称
        String topic = "my-topic";

        // Kafka Producer 配置
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建 Kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 发送消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "Test message");
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println("Message sent successfully");
                }
            }
        });

        // 关闭 Kafka Producer
        producer.close();
    }
}
