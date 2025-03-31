# Consumer Commit types

---

## **1️⃣ Auto-Commit (commit happens automatically)**
Kafka automatically commits the offset after processing messages, controlled by:

```java
consumerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
```
This is **simple** but **risky** because:
- If the consumer crashes after polling but before processing, messages may be lost.
- If processing is slow, messages might be reprocessed on restart.

✅ **Example:**
```java
Properties consumerProperties = new Properties();
consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "auto-commit-group");
consumerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");  // Auto-commit enabled
consumerProperties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000"); // Commit every 1 second

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
consumer.subscribe(Collections.singletonList("test-topic"));

while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    for (ConsumerRecord<String, String> record : records) {
        System.out.println("Consumed: " + record.value());
    }
}
```

---

## **2️⃣ Synchronous Commit (`commitSync()`)**
This ensures that offsets are **committed successfully** before proceeding.

✅ **Pros:**
- Guarantees that offsets are committed **before moving forward**.
- Prevents duplicate processing in case of consumer failure.

❌ **Cons:**
- Blocks execution until the commit succeeds, affecting performance.

✅ **Example:**
```java
consumerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");  // Disable auto-commit

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
consumer.subscribe(Collections.singletonList("test-topic"));

try {
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("Consumed: " + record.value());
        }
        consumer.commitSync();  // Blocking commit
        System.out.println("✅ Offsets committed synchronously.");
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    consumer.close();
}
```

---

## **3️⃣ Asynchronous Commit (`commitAsync()`)**
Offsets are committed **without blocking execution**, improving performance.

✅ **Pros:**
- Faster than `commitSync()`, as it doesn't block execution.
- Can handle failures using a callback function.

❌ **Cons:**
- Since it's asynchronous, there's a chance that offsets aren't committed in order.
- **Solution:** Combine `commitSync()` at shutdown to avoid data loss.

✅ **Example:**
```java
consumerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
consumer.subscribe(Collections.singletonList("test-topic"));

try {
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("Consumed: " + record.value());
        }
        consumer.commitAsync((offsets, exception) -> {
            if (exception != null) {
                System.err.println("❌ Commit failed: " + exception.getMessage());
            } else {
                System.out.println("✅ Offsets committed asynchronously: " + offsets);
            }
        });
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    try {
        consumer.commitSync();  // Ensures the last offsets are committed before shutdown
    } finally {
        consumer.close();
    }
}
```

---

## **Which One Should You Use?**
| Commit Technique  | Pros | Cons | When to Use? |
|------------------|------|------|--------------|
| **Auto-Commit** | Simple, non-blocking | Risk of message loss | If message loss is not a concern |
| **commitSync()** | Ensures successful commit | Slows down processing | If you need strong guarantees |
| **commitAsync()** | Fast, non-blocking | Might commit out of order | When performance is important |

🔹 **Best Practice:** **Use `commitAsync()` during processing and `commitSync()` at shutdown** to prevent data loss.

---

Let me know if you need further explanations! 🚀