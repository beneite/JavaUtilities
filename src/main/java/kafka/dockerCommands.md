# Kafka CLI Commands

## 1. Create a Kafka Topic

```sh
kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### Command Breakdown:
- **kafka-topics.sh**
    - This is a Kafka CLI script used to manage topics (create, list, delete, describe).
    - Available inside the Kafka container at `/opt/bitnami/kafka/bin/kafka-topics.sh`.
- **--create**
    - Specifies that we want to create a new topic.
- **--topic my-topic**
    - The name of the topic being created (`my-topic` in this case).
    - You can replace `my-topic` with any name you prefer.
- **--bootstrap-server localhost:9092**
    - Defines the Kafka broker address to connect to.
    - `localhost:9092` refers to the local Kafka broker running on port 9092.
- **--partitions 1**
    - Defines the number of partitions for the topic.
    - Partitions allow parallel processing; more partitions enable better scalability.
- **--replication-factor 1**
    - Sets the number of replicas for fault tolerance.
    - Since we have a single Kafka broker (no cluster), `1` is used.
    - In production, this should be `>=2` for redundancy.

## 2. List Kafka Topics

```sh
kafka-topics.sh --list --bootstrap-server localhost:9092
```

### What This Command Does?
- Connects to the Kafka broker running on `localhost:9092`.
- Lists all existing Kafka topics in the cluster.

## 3. Describe Kafka Topics

```sh
kafka-topics.sh --describe --bootstrap-server localhost:9092
```
