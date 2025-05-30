package kafka.p13_consumerConsumptionTypes;

import kafka.constantsClass.Constants;
import kafka.p11_ConsumerCommitsTypes.ConsumerCallback;
import kafka.utilities.CommonUtils;
import kafka.utilities.KafkaUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FindMessageInTopicWithConsumerKey {

    /** over here the objective is to fetch the message from the topic after the consumer subscribes to the topic and then we will use the key to find the message
     * Step 1. When your test starts, push a message to the topic.
     * Step 2. you subscribe to the topic.
     * Step 3.You then trigger the action that should produce the Kafka message.
     * Step 4.Your consumer will only see messages produced after subscribing.
     * Pre-Requisite:
     * 1. Run the docker container 'kafka/p01_kafkaThroughDocker/docker-compose.yaml'
     * 2. Run 'docker exec -it kafka-container /bin/sh' to enter inside kafka-container
     * 3. run command 'kafka-topics.sh --create --topic topic-with-5-partitions --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1' to create topic.
     */
    public static final String TOPIC_WITH_5_PARTITIONS = "topic-with-5-partitions";
    @Test
    public void producerAndConsumerCombined(){
        String keyOne = CommonUtils.generateUniqueKey();
        // Step 1. When your test starts, push a message to the topic.
        KafkaUtils.produceAndGetMetadata(Constants.LOCALHOST_29092, TOPIC_WITH_5_PARTITIONS, keyOne, "Message for Ashish Mishra with key="+keyOne);

        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCALHOST_29092);
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");  // in this case we need to manually commit the message
        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "automation-consumer-group");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        System.out.println("🟢 Consumer Subscribed !!!");
        System.out.println("*********************************************");
        // Step 2. you subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC_WITH_5_PARTITIONS));

        String keyTwo = CommonUtils.generateUniqueKey();
        // Step 3.You then trigger the action that should produce the Kafka message
        // pushing 2nd message into the queue
        KafkaUtils.produceAndGetMetadata(Constants.LOCALHOST_29092, TOPIC_WITH_5_PARTITIONS, keyTwo, "Message for Ashish Mishra with key="+keyTwo);

            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(60));
            System.out.println("🟢 Consumer Online...");
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                if(consumerRecord.key().equals(keyTwo)) {       // will only fetch the message with key as KeyTwo
                    System.out.println("*********************************************");
                    System.out.println("✅ [Topic] " + consumerRecord.topic());
                    System.out.println("✅ [Partition] " + consumerRecord.partition());
                    System.out.println("✅ [Offset] " + consumerRecord.offset());
                    System.out.println("Consumer key:" + consumerRecord.key());
                    System.out.println("Consumer value:" + consumerRecord.value());
                }
            }
        KafkaUtils.closeKafkaConsumer(consumer);
    }
}
