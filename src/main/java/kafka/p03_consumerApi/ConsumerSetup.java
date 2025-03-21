package kafka.p03_consumerApi;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerSetup {

    @Test
    public void consumingSetupAndConsumingMessage(){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:29092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("auto.offset.reset", "earliest");
        properties.setProperty("group.id", "simple-topic-group-new");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("my-topic"));

        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(60));

        for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
            System.out.println("Consumer key:"+consumerRecord.key());
            System.out.println("Consumer value:"+consumerRecord.value());
        }

        consumer.close();
    }

}
