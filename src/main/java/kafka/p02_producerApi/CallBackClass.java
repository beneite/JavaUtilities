package kafka.p02_producerApi;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CallBackClass implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("✅ Record sent successfully!");
        System.out.println("✅ [Partition]: " + recordMetadata.partition());
        System.out.println("✅ [Offset]: " + recordMetadata.offset());
    }
}
