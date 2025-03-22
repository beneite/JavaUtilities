package kafka.p06_customDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {

    /**
     * A deserializer in Kafka is responsible for converting the raw byte array (received from Kafka) back into a usable Java object.
     * When a producer sends a message, Kafka stores it as bytes.
     * The consumer needs a deserializer to transform those bytes back into the original object format.
     */
    @Override
    public Order deserialize(String s, byte[] bytes) {
        Order order;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            order = objectMapper.readValue(bytes, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return order;
    }
}
