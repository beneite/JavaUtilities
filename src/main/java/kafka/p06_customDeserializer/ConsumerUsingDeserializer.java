package kafka.p06_customDeserializer;

import kafka.constantsClass.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerUsingDeserializer {

    @Test
    public void consumingSetupAndConsumingMessage(){
        Properties properties = new Properties();
        properties.setProperty(Constants.BOOTSTRAP_SERVER, Constants.LOCALHOST_29092);
        properties.setProperty(Constants.KEY_DESERIALIZER, StringDeserializer.class.getName());
        properties.setProperty(Constants.VALUE_DESERIALIZER, OrderDeserializer.class.getName());
        properties.setProperty("auto.offset.reset", "earliest");
        properties.setProperty("group.id", "simple-topic-group-new");

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("my-topic"));

        ConsumerRecords<String, Order> consumerRecords = consumer.poll(Duration.ofSeconds(60));

        for(ConsumerRecord<String, Order> consumerRecord : consumerRecords){
            System.out.println("****************************************");
            Order order = consumerRecord.value();
            System.out.println("Customer name:"+ order.getCustomerName());
            System.out.println("Product name:"+ order.getProduct());
            System.out.println("Quantity:"+ order.getQuantity());
        }

        consumer.close();
    }
}
