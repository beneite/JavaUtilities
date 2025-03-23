# Here’s a **comprehensive list of Kafka Consumer configurations** along with their **usage**:

---

## **1️⃣ Basic Consumer Configurations**
| **Property**           | **Description** | **Recommended Value** |
|------------------------|---------------|----------------------|
| `bootstrap.servers`    | List of Kafka brokers to connect to. | `"localhost:9092"` |
| `group.id`            | Consumer **group ID** for load balancing & offset tracking. | `"my-consumer-group"` |
| `key.deserializer`    | Deserializer for message keys (e.g., `StringDeserializer`). | `"org.apache.kafka.common.serialization.StringDeserializer"` |
| `value.deserializer`  | Deserializer for message values. | `"org.apache.kafka.common.serialization.StringDeserializer"` |

---

## **2️⃣ Polling & Message Consumption**
| **Property**            | **Description** | **Recommended Value** |
|-------------------------|---------------|----------------------|
| `auto.offset.reset`     | Controls behavior when **no committed offset** is found.<br> - `earliest`: Read from the beginning.<br> - `latest`: Read new messages only.<br> - `none`: Throw an error if no offset is found. | `"earliest"` (for consuming old messages) |
| `max.poll.records`      | Maximum **number of records** returned per poll. | `500` (default: `500`) |
| `fetch.min.bytes`       | Minimum data (bytes) fetched per poll (low value = more frequent polling). | `1` (default) |
| `fetch.max.bytes`       | Maximum data (bytes) fetched per poll. | `50MB` (default) |

---

## **3️⃣ Offset Management & Reliability**
| **Property**                | **Description** | **Recommended Value** |
|-----------------------------|---------------|----------------------|
| `enable.auto.commit`        | Automatically commits offsets after processing.<br> - `true`: Kafka commits offsets automatically.<br> - `false`: The app must commit manually. | `false` (for **manual control**) |
| `auto.commit.interval.ms`   | If `enable.auto.commit=true`, this sets commit interval. | `5000` ms (5 sec) |
| `session.timeout.ms`        | If no heartbeat from the consumer, Kafka considers it **dead** and reassigns partitions. | `45000` ms |
| `heartbeat.interval.ms`     | Frequency of heartbeats to Kafka brokers. | `15000` ms |
| `max.poll.interval.ms`      | Max time between polls before Kafka **removes** the consumer from the group. | `300000` ms (5 min) |

---

## **4️⃣ Performance & Throughput Optimization**
| **Property**               | **Description** | **Recommended Value** |
|----------------------------|---------------|----------------------|
| `fetch.min.bytes`          | Minimum amount of data (bytes) before fetching messages. | `1` |
| `fetch.max.wait.ms`        | Max time (ms) Kafka waits before sending messages, even if `fetch.min.bytes` is not met. | `500` ms |
| `max.partition.fetch.bytes`| Max data fetched from each partition per request. | `1MB` (default) |

---

## **5️⃣ Security Settings**
| **Property**               | **Description** |
|----------------------------|---------------|
| `security.protocol`        | Security protocol (`PLAINTEXT`, `SSL`, `SASL_PLAINTEXT`, `SASL_SSL`). |
| `sasl.mechanism`          | Authentication method (`PLAIN`, `SCRAM-SHA-256`, `GSSAPI`, etc.). |
| `ssl.truststore.location` | Location of SSL truststore for encryption. |

---

## **6️⃣ Example Consumer Configuration**
```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("group.id", "my-consumer-group");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

// Offset Management
props.put("enable.auto.commit", "false");  // Manual offset commit
props.put("auto.offset.reset", "earliest");

// Performance Optimizations
props.put("max.poll.records", "500");
props.put("fetch.min.bytes", "1");
props.put("fetch.max.wait.ms", "500");

// Heartbeat & Session Timeout
props.put("session.timeout.ms", "45000");
props.put("heartbeat.interval.ms", "15000");

// Security
props.put("security.protocol", "SSL");
props.put("ssl.truststore.location", "/path/to/truststore.jks");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Collections.singletonList("my-topic"));
```

---

### **🔹 Summary**
- **For message reliability →** Use `enable.auto.commit=false` and manually commit offsets.
- **For performance tuning →** Adjust `fetch.min.bytes`, `max.poll.records`, and `fetch.max.wait.ms`.
- **For security →** Configure `SSL` or `SASL`.

Would you like recommendations based on your specific use case? 🚀