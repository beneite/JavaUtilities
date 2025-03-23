package kafka.p07_customPartitioner;

import kafka.constantsClass.Constants;
import kafka.utilities.CommonUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ProducerAndConsumer {

    public static final String TOPIC_MULTI_PARTITIONER = "topic-multi-partitioner";

    /**
     * ********************************************************************************************************************
     * An Async Producer (Asynchronous Kafka Producer) is a Kafka producer that sends messages without blocking the execution of the program.
     * It uses a callback mechanism to handle the acknowledgment when the message is successfully sent or encounters an error.
     * ********************************************************************************************************************
     * Pre-Requisite:
     * 1. Run the docker container 'kafka/p01_kafkaThroughDocker/docker-compose.yaml'
     * 2. Run 'docker exec -it kafka-container /bin/sh' to enter inside kafka-container
     * 3. run command 'kafka-topics.sh --create --topic topic-multi-partitioner --bootstrap-server localhost:9092 --partitions 5 --replication-factor 1' to create topic.
     */

    @Test
    public void producerAndConsumerCombined(){
        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCALHOST_29092);
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties)) {
            String key = CommonUtils.generateUniqueKey();
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_MULTI_PARTITIONER, key, "Message with key = "+key);
            System.out.println("🟢 Sending record as Sync...");
            RecordMetadata recordMetadata = producer.send(record).get();

            System.out.println("✅ Producer generated record: " + record);
            System.out.println("✅ [Topic] " + recordMetadata.topic());
            System.out.println("✅ [Partition] " + recordMetadata.partition());
            System.out.println("✅ [Offset] " + recordMetadata.offset());

        } catch (Exception e) {
            System.err.println("❌ Error sending record: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("🛑 Producer closed.");
        }

        System.out.println("***************************************************************************************************************************************");

        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCALHOST_29092);
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "simple-topic-group"+CommonUtils.generateUniqueKey()); // adding generateUniqueKey to consumer group so that everytime it is unique and start from starting

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(Collections.singletonList(TOPIC_MULTI_PARTITIONER));

        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(60));
        System.out.println("🟢 Consumer Online...");
        for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
            System.out.println("*********************************************");
            System.out.println("✅ [Topic] "+consumerRecord.topic());
            System.out.println("✅ [Partition] "+consumerRecord.partition());
            System.out.println("✅ [Offset] "+consumerRecord.offset());
            System.out.println("Consumer key:"+consumerRecord.key());
            System.out.println("Consumer value:"+consumerRecord.value());
        }

        consumer.close();


    }

}
