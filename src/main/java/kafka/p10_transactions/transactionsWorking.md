# **Kafka Transactions: Ensuring Atomicity in Message Processing**

Kafka **transactions** allow producers to **send multiple messages atomically** across topics and partitions. This ensures that either **all messages are committed** or **none are** (avoiding partial writes).

---

## **1️⃣ Why Use Transactions in Kafka?**
Transactions are useful in scenarios where:  
✅ **Atomicity**: Ensure **all** related messages are produced together or not at all.  
✅ **Exactly-Once Processing (EOS)**: Guarantees that messages are processed exactly once (with proper consumer settings).  
✅ **Avoid Partial Processing**: If a failure occurs mid-processing, **all messages are rolled back**.

---

## **2️⃣ Key Configurations for Transactions**
| **Config**                  | **Description** | **Recommended Value** |
|-----------------------------|----------------|----------------------|
| `transactional.id`          | Unique ID for the producer transaction. | `"my-transactional-producer"` |
| `enable.idempotence`        | Ensures duplicate messages are not sent. | `true` (must be enabled) |
| `acks`                      | Controls acknowledgment level. | `all` (must be `all`) |
| `transaction.timeout.ms`    | Timeout for transactions before aborting. | `60000` (1 minute) |

---

## **3️⃣ How Kafka Transactions Work**
Kafka transactions follow a **begin → send → commit/abort** model:

1️⃣ **Begin Transaction**: Start a new transaction.  
2️⃣ **Produce Messages**: Send messages to multiple partitions/topics.  
3️⃣ **Commit Transaction**: If successful, commit all messages together.  
4️⃣ **Abort Transaction**: If a failure occurs, roll back everything.

---

## **4️⃣ Example: Producer with Transactions**
```java
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaTransactionalProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Enable transactions
        props.put("transactional.id", "my-transactional-producer");
        props.put("enable.idempotence", "true");
        props.put("acks", "all");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Initialize transaction
        producer.initTransactions();

        try {
            producer.beginTransaction();

            // Send messages atomically
            producer.send(new ProducerRecord<>("topicA", "key1", "Message for Topic A"));
            producer.send(new ProducerRecord<>("topicB", "key2", "Message for Topic B"));

            // Commit transaction
            producer.commitTransaction();
            System.out.println("Transaction committed successfully!");

        } catch (Exception e) {
            producer.abortTransaction();  // Rollback in case of failure
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
```

---

## **5️⃣ How Consumers Handle Transactions**
To **consume transactional messages**, consumers must be configured with:
```java
props.put("isolation.level", "read_committed");
```
| **Isolation Level**     | **Behavior** |
|------------------------|-------------|
| `read_uncommitted`     | Reads **all messages**, including **aborted** ones. |
| `read_committed` (default) | Reads **only committed messages** (recommended). |

---

## **6️⃣ Example: Consumer with Transactional Support**
```java
import org.apache.kafka.clients.consumer.*;

import java.util.Collections;
import java.util.Properties;

public class KafkaTransactionalConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "transactional-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Ensure we only read committed messages
        props.put("isolation.level", "read_committed");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("topicA"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received: " + record.value());
            }
        }
    }
}
```

---

## **7️⃣ When to Use Kafka Transactions?**
| **Use Case** | **Why Use Transactions?** |
|-------------|----------------------|
| **Multi-topic writes** | Ensure messages across **multiple topics** are written atomically. |
| **Database & Kafka Sync** | Avoid partial writes when synchronizing **Kafka & databases**. |
| **Exactly-once processing (EOS)** | Prevent duplicate or lost messages. |
| **Workflow consistency** | Ensure all steps in a process **either succeed or fail together**. |

---

## **8️⃣ Summary**
✅ **Kafka Transactions** → Ensure **atomicity**, **consistency**, and **exactly-once processing**.  
✅ **Producers must use** → `transactional.id`, `enable.idempotence=true`, `acks=all`.  
✅ **Consumers must use** → `isolation.level=read_committed` to ignore aborted messages.  
✅ **Transactional messages are either committed or fully rolled back** (no partial writes).

Would you like a real-world example, such as integrating transactions with a database? 🚀