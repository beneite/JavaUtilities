package kafka.p04_singleProducerConsumer;

import kafka.constantsClass.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ProducerAndConsumer {

    public static final String TOPIC_NAME_MY_TOPIC = "my-topic";

    /**
     * ********************************************************************************************************************
     * An Async Producer (Asynchronous Kafka Producer) is a Kafka producer that sends messages without blocking the execution of the program.
     * It uses a callback mechanism to handle the acknowledgment when the message is successfully sent or encounters an error.
     * ********************************************************************************************************************
     * Pre-Requisite:
     * 1. Run the docker container 'kafka/p01_kafkaThroughDocker/docker-compose.yaml'
     * 2. Run 'docker exec -it kafka-container /bin/sh' to enter inside kafka-container
     * 3. run command 'kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1' to create topic.
     */

    @Test
    public void producerAndConsumerCombined(){
        Properties producerProperties = new Properties();
        producerProperties.setProperty(Constants.BOOTSTRAP_SERVER, Constants.LOCALHOST_29092);
        producerProperties.setProperty(Constants.KEY_SERIALIZER, Constants.STRING_SERIALIZER);
        producerProperties.setProperty(Constants.VALUE_SERIALIZER, Constants.STRING_SERIALIZER);

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME_MY_TOPIC, "key-2", "Message for Ashish Mishra via sync");
            System.out.println("🟢 Sending record as Sync...");
            RecordMetadata recordMetadata = producer.send(record).get();

            System.out.println("✅ Producer generated record: " + record);
            System.out.println("✅ [Partition] " + recordMetadata.partition());
            System.out.println("✅ [Offset] " + recordMetadata.offset());

        } catch (Exception e) {
            System.err.println("❌ Error sending record: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("🛑 Producer closed.");
        }

        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(Constants.BOOTSTRAP_SERVER, Constants.LOCALHOST_29092);
        consumerProperties.setProperty(Constants.KEY_DESERIALIZER, Constants.STRING_DESERIALIZER);
        consumerProperties.setProperty(Constants.VALUE_DESERIALIZER, Constants.STRING_DESERIALIZER);
        consumerProperties.setProperty("auto.offset.reset", "earliest");
        consumerProperties.setProperty("group.id", "simple-topic-group");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME_MY_TOPIC));

        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(60));
        System.out.println("🟢 Consumer Online...");
        for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
            System.out.println("Consumer key:"+consumerRecord.key());
            System.out.println("Consumer value:"+consumerRecord.value());
        }

        consumer.close();


    }

}
