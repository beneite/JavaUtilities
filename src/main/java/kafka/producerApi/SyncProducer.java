package kafka.producerApi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;

import java.util.Properties;

public class SyncProducer {

    @Test
    public void synchronousSendDataThroughProducer(){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, Integer> record = new ProducerRecord<>("my-topic","key-1", 100011);

        try{
            System.out.println("🟢 Sending record...");
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
