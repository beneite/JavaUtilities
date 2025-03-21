package kafka.p02_producerApi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.testng.annotations.Test;

import java.util.Properties;

public class AsyncProducer {

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
    public void sendDataThroughProducer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:29092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key-1", "Message for Ashish Mishra via Async");

        try {
            System.out.println("🟢 Sending record as Async...");
            producer.send(record, new CallBackClass());

        } catch (Exception e) {
            System.err.println("❌ Error sending record: " + e.getMessage());
            e.printStackTrace();
        } finally {
            producer.close();
            System.out.println("🛑 Producer closed.");
        }
    }

}
