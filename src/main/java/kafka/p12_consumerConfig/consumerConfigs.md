# Here is a **comprehensive list** of **Kafka Consumer Configurations** along with their descriptions:

---

### **1️⃣ Basic Consumer Configs**
| Config | Default | Description |
|--------|---------|------------|
| `bootstrap.servers` | (Required) | Comma-separated list of Kafka broker addresses (`host:port`). |
| `group.id` | (Required) | Consumer group ID for identifying consumers in a group. |
| `client.id` | "" (empty) | Logical name for the consumer to help debugging. |

---

### **2️⃣ Offset Management Configs**
| Config | Default | Description |
|--------|---------|------------|
| `enable.auto.commit` | `true` | Whether to auto-commit offsets. (`true` or `false`) |
| `auto.commit.interval.ms` | `5000` | Frequency (ms) of auto-committing offsets. |
| `auto.offset.reset` | `"latest"` | What to do when there’s no offset for a partition: <br> - `"latest"` → Start consuming from the latest message. <br> - `"earliest"` → Start from the beginning of the partition. <br> - `"none"` → Throws error if no offset is found. |
| `isolation.level` | `"read_uncommitted"` | **For Kafka transactions:** <br> - `"read_uncommitted"` → Read all messages (even uncommitted). <br> - `"read_committed"` → Read only committed messages. |

---

### **3️⃣ Polling & Fetching Configs**
| Config | Default | Description |
|--------|---------|------------|
| `max.poll.records` | `500` | Maximum number of records to fetch in one `poll()`. |
| `max.poll.interval.ms` | `300000` (5 min) | Maximum time before the consumer is considered inactive. |
| `fetch.min.bytes` | `1` | Minimum amount of data the broker should return in a fetch request. |
| `fetch.max.bytes` | `52428800` (50MB) | Maximum amount of data the consumer can fetch in one request. |
| `fetch.max.wait.ms` | `500` | Maximum time (ms) the broker waits to gather data before responding. |

---

### **4️⃣ Heartbeat & Session Timeout Configs**
| Config | Default | Description |
|--------|---------|------------|
| `heartbeat.interval.ms` | `3000` | Time between heartbeats to Kafka. Used to detect failures. |
| `session.timeout.ms` | `45000` | Time after which the broker will remove a consumer if no heartbeat is received. |

---

### **5️⃣ Consumer Threading & Performance Configs**
| Config | Default | Description |
|--------|---------|------------|
| `max.partition.fetch.bytes` | `1048576` (1MB) | Max bytes per partition that the consumer fetches in one request. |
| `allow.auto.create.topics` | `true` | Allows automatic creation of topics when subscribing to them. |
| `default.api.timeout.ms` | `60000` | Default timeout for Kafka API calls. |

---

### **6️⃣ Deserialization Configs**
| Config | Default | Description |
|--------|---------|------------|
| `key.deserializer` | (Required) | Deserializer class for message keys (e.g., `StringDeserializer`). |
| `value.deserializer` | (Required) | Deserializer class for message values (e.g., `StringDeserializer`). |

---

### **7️⃣ Security Configs**
| Config | Default | Description |
|--------|---------|------------|
| `security.protocol` | `"PLAINTEXT"` | Security protocol (`SSL`, `SASL_SSL`, `SASL_PLAINTEXT`, `PLAINTEXT`). |
| `ssl.keystore.location` | (None) | Location of the keystore file for SSL authentication. |
| `ssl.truststore.location` | (None) | Location of the truststore file for SSL authentication. |
| `sasl.mechanism` | `"GSSAPI"` | Authentication mechanism (`PLAIN`, `SCRAM-SHA-256`, `SCRAM-SHA-512`). |

---

### **8️⃣ Logging & Monitoring Configs**
| Config | Default | Description |
|--------|---------|------------|
| `metrics.recording.level` | `"INFO"` | Logging level (`INFO`, `DEBUG`, `TRACE`). |
| `metric.reporters` | (None) | Custom metrics reporters. |
| `connections.max.idle.ms` | `540000` | Maximum time a connection can be idle before closing. |

---

### **9️⃣ Consumer Rebalancing Configs**
| Config | Default | Description |
|--------|---------|------------|
| `partition.assignment.strategy` | `"range"` | Strategy for partition assignment: <br> - `"range"` → Assigns partitions evenly among consumers. <br> - `"roundrobin"` → Assigns partitions in a round-robin manner. <br> - `"sticky"` → Attempts to retain previous assignments. |

---

## **🔹 Example: Full Consumer Config**
```java
Properties consumerProperties = new Properties();
consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
consumerProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
consumerProperties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
consumerProperties.setProperty(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "3000");
consumerProperties.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "45000");
```

---

## **🚀 Best Practices**
✔ **Disable Auto-Commit (`enable.auto.commit=false`)** to avoid data loss.  
✔ **Use `commitSync()` + `commitAsync()` together** for offset commits.  
✔ **Set `auto.offset.reset=earliest`** if you need to read old messages.  
✔ **Increase `max.poll.records`** for higher throughput.  
✔ **Use `session.timeout.ms` and `heartbeat.interval.ms` wisely** to prevent rebalancing issues.

---

This should cover **everything** you need for configuring a Kafka Consumer! 🎯  
Let me know if you need specific **tuning** suggestions for performance! 🚀