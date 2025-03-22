package kafka.p05_customSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {

    /**
     * The class implements Kafka’s Serializer<Order> interface,
     * making it responsible for converting Order objects into a byte array before sending them to Kafka.
     * @param s - topic
     * @param order - order
     * @return byte[]
     */

    @Override
    public byte[] serialize(String s, Order order) {
        byte[] response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response = objectMapper.writeValueAsBytes(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
