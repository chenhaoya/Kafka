package com.ch.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * @author ChenHao
 * @date 2023/9/3 16:58
 */
public class CustomProducer {
    public static void main(String[] args) {
        // 0、配置
        Properties properties = new Properties();

        // 1、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 2、发送数据

        // 3、关闭资源
    }
}