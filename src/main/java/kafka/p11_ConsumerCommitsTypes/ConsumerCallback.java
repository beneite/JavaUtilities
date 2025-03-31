package kafka.p11_ConsumerCommitsTypes;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

public class ConsumerCallback implements OffsetCommitCallback {
    @Override
    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
        System.out.println("🟢 ConsumerCallback executed.");

        if (exception != null) {
            System.err.println("❌ Offset commit failed: " + exception.getMessage());
            exception.printStackTrace();
            return;
        }

        System.out.println("✅ Commit Success for below offsets:");
        offsets.forEach((topicPartition, offset) ->
                System.out.printf("Topic: %s, Partition: %d, Offset: %d%n",
                        topicPartition.topic(), topicPartition.partition(), offset.offset()));
    }
}
