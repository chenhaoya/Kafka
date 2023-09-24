package com.ch.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author ChenHao
 * @date 2023/9/3 16:58
 */
public class CustomProducer {
    public static void main(String[] args) {

        // 0、配置
        Properties properties = new Properties();
        try {
            properties.load(CustomProducer.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String bootstrapServers = properties.getProperty("kafka.bootstrap.servers");
        System.out.println(bootstrapServers);
        // 连接kafka
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // 指定对应k、v序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        System.out.println(0);
        // 1、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        System.out.println(1);
        // 2、发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("my-topic", "java" + i), (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                    // 处理发送失败的情况
                } else {
                    System.out.println("Message sent successfully");
                }
            });
        }



        // 3、关闭资源
        kafkaProducer.close();
    }
}