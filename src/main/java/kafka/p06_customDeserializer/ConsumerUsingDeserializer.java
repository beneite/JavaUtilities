package kafka.p06_customDeserializer;

import kafka.constantsClass.Constants;
import kafka.utilities.CommonUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
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
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCALHOST_29092);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "simple-topic-group-new"+ CommonUtils.generateUniqueKey());

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
