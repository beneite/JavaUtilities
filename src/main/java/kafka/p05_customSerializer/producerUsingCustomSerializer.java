package kafka.p05_customSerializer;

import kafka.constantsClass.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.testng.annotations.Test;

import java.util.Properties;

public class producerUsingCustomSerializer {

    /**
     * ********************************************************************************************************************
     * A Sync Producer (Synchronous Kafka Producer) is a Kafka producer that waits for an acknowledgment from the Kafka broker before proceeding.
     * In the provided code, the .get() method is used on the send(record) call,
     * making it a blocking call that waits until the message is successfully sent and metadata is returned.
     * ********************************************************************************************************************
     * Pre-Requisite:
     * 1. Run the docker container 'kafka/kafkaThroughDocker/docker-compose.yaml'
     * 2. Run 'docker exec -it kafka-container /bin/sh' to enter inside kafka-container
     * 3. run command 'kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1' to create topic.
     */
    @Test
    public void verifyProducerUsingCustomSerializer(){
        Properties properties = new Properties();
        properties.setProperty(Constants.BOOTSTRAP_SERVER, Constants.LOCALHOST_29092);
        properties.setProperty(Constants.KEY_SERIALIZER, StringSerializer.class.getName());
        properties.setProperty(Constants.VALUE_SERIALIZER, OrderSerializer.class.getName()); // using custom serializer

        Order order = new Order("Ashish Mishra","Nestle Maggi", 20);

        KafkaProducer<String, Order> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, Order> record = new ProducerRecord<>("my-topic", "key-1", order);

        try{
            System.out.println("🟢 Sending record as Sync...");
            RecordMetadata recordMetadata = producer.send(record).get(); // Blocking call

            System.out.println("✅ Producer generated record: " + record);
            System.out.println("✅ [Partition] " + recordMetadata.partition());
            System.out.println("✅ [Offset] " + recordMetadata.offset());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
