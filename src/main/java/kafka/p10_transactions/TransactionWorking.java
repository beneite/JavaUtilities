package kafka.p10_transactions;

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

public class TransactionWorking {

    public static final String TOPIC_WITH_5_PARTITIONS = "topic-with-5-partitions";

    /**
     * ********************************************************************************************************************
     * Kafka transactions allow producers to send multiple messages atomically across topics and partitions.
     * This ensures that either all messages are committed or none are (avoiding partial writes).
     * ********************************************************************************************************************
     * Pre-Requisite:
     * 1. Run the docker container 'kafka/p01_kafkaThroughDocker/docker-compose.yaml'
     * 2. Run 'docker exec -it kafka-container /bin/sh' to enter inside kafka-container
     * 3. run command 'kafka-topics.sh --create --topic topic-with-5-partitions --bootstrap-server localhost:9092 --partitions 5 --replication-factor 1' to create topic.
     */

    @Test
    public void initiateTransaction(){
        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCALHOST_29092);
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        producerProperties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction-id-1");
        producerProperties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "5000");

        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties);
        try {
            producer.initTransactions();        // initializing the transaction

            String key = CommonUtils.generateUniqueKey();
            ProducerRecord<String, String> recordOne = new ProducerRecord<>(TOPIC_WITH_5_PARTITIONS, key, "Message One for Ashish Mishra with key="+key);
            ProducerRecord<String, String> recordTwo = new ProducerRecord<>(TOPIC_WITH_5_PARTITIONS, key, "Message Two for Ashish Mishra with key="+key);

            producer.beginTransaction();        // beginning the transaction

            System.out.println("🟢 Sending record ONE");
            producer.send(recordOne);
            System.out.println("🟢 Sending record TWO");
            producer.send(recordTwo);

            producer.commitTransaction();       // committing the transaction
        } catch (Exception e) {
            System.err.println("❌ Error sending record: " + e.getMessage());
            producer.abortTransaction();                // aborting the entire transaction on failure.
        } finally {
            producer.close();
            System.out.println("🛑 Producer closed.");
        }
        System.out.println("***************************************************************************************************************************************");

        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCALHOST_29092);
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // Ensure we only read committed messages
        consumerProperties.setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "simple-topic-group"+CommonUtils.generateUniqueKey());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(Collections.singletonList(TOPIC_WITH_5_PARTITIONS));

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
