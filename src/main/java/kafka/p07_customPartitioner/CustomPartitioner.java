package kafka.p07_customPartitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {
    /**
     * Computes the partition for a given Kafka record based on the key.
     *
     * @param topic      The name of the Kafka topic to which the record is being sent.
     * @param key        The key of the record (used for partitioning).
     * @param keyBytes   The serialized key in byte format.
     * @param value      The value of the record.
     * @param valueBytes The serialized value in byte format.
     * @param cluster    Metadata about the Kafka cluster, including available partitions.
     * @return The computed partition number for the record.
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List< PartitionInfo> partitionInfos = cluster.availablePartitionsForTopic(topic);
        return Math.abs(Utils.murmur2(keyBytes)) % partitionInfos.size();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
