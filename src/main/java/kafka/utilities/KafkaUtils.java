package kafka.utilities;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public final class KafkaUtils {

    private KafkaUtils(){}

    /**
     * Sends a key-value message to a specified Kafka topic and returns metadata about the record.
     * <p>
     * This method uses a synchronous Kafka producer to send a message to the provided topic.
     * The method returns a {@link RecordMetadata} object containing partition and offset info
     * if the message is successfully sent. If an exception occurs, the method logs the error
     * and returns {@code null}.
     * </p>
     *
     * @param bootstrapServer the Kafka bootstrap server address (e.g., "localhost:9092")
     * @param topicName       the name of the Kafka topic to send the message to
     * @param key             the key to associate with the message (can affect partitioning)
     * @param message         the message value to be sent to the topic
     * @return the {@link RecordMetadata} containing partition and offset of the sent message,
     *         or {@code null} if sending fails
     */
    public static RecordMetadata produceAndGetMetadata(String bootstrapServer, String topicName, String key, String message){
        RecordMetadata recordMetadata = null;
        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, message);
            System.out.println("🟢 Sending record as Sync...");
            recordMetadata = producer.send(record).get();

            System.out.println("✅ Producer generated record: " + record);
            System.out.println("✅ [Partition] " + recordMetadata.partition());
            System.out.println("✅ [Offset] " + recordMetadata.offset());

        } catch (Exception e) {
            System.err.println("❌ Error sending record: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("🛑 Producer closed.");
        }
        System.out.println("***************************************************************************************************************************************");

        return recordMetadata;
    }

    /**
     * Gracefully closes the provided KafkaConsumer instance.
     *
     * <p>This method ensures the consumer leaves the group cleanly,
     * freeing up partitions and avoiding potential rebalance issues.</p>
     *
     * <p>The method is generic and can accept a KafkaConsumer with any key and value types.</p>
     *
     * @param <K> the type of keys consumed by the KafkaConsumer
     * @param <V> the type of values consumed by the KafkaConsumer
     * @param consumer the KafkaConsumer instance to close; may be {@code null}
     */
    public static <K, V> void closeKafkaConsumer(KafkaConsumer<K, V> consumer) {
        if (consumer != null) {
            try {
                consumer.close();
                System.out.println("✅ Kafka consumer closed successfully.");
            } catch (Exception e) {
                System.err.println("❌ Failed to close Kafka consumer: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ Kafka consumer is null, skipping close.");
        }
    }
}
