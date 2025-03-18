package kafka.producerApi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import java.util.Properties;

public class ProducerApiMethods {

    @Test
    public void sendDataThroughProducer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.setProperty("acks", "all");  // Ensure Kafka acknowledges messages
        properties.setProperty("retries", "3"); // Retry sending on failure
        properties.setProperty("request.timeout.ms", "30000"); // Set timeout to 30s

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, Integer> record = new ProducerRecord<>("my-topic", "key-1", 10);

        try {
            System.out.println("🟢 Sending record...");
            RecordMetadata metadata = producer.send(record).get(); // Blocking call

            System.out.println("✅ Record sent successfully!");
            System.out.println("✅ [Partition]: " + metadata.partition());
            System.out.println("✅ [Offset]: " + metadata.offset());
        } catch (Exception e) {
            System.err.println("❌ Error sending record: " + e.getMessage());
            e.printStackTrace();
        } finally {
            producer.close();
            System.out.println("🛑 Producer closed.");
        }
    }

}
