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
        properties.setProperty("bootstrap.servers", "localhost:29092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key-2", "Key 2 bhej diya");

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
