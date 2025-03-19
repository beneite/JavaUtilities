# Kafka and Kafdrop Setup using Docker Compose

## Overview
This `docker-compose.yml` file sets up a **Kafka** broker along with **Zookeeper** and **Kafdrop UI** using Docker containers. The setup allows Kafka to be accessed both **inside Docker** (for Kafdrop) and **from the host machine** (for external applications like Java producers/consumers).

---

## Services in `docker-compose.yml`

### 1️⃣ **Zookeeper (`zookeeper-service`)**
Zookeeper is required for Kafka to manage its brokers and distributed state.

```yaml
  zookeeper-service:
    image: zookeeper:3.9.3-jre-17
    container_name: zookeeper-container
    ports:
      - "2181:2181"
```
✅ **Key Points:**
- Uses the official `zookeeper` image.
- Listens on **port 2181** (required by Kafka).

---

### 2️⃣ **Kafka Broker (`kafka-service`)**
Kafka is the message broker responsible for handling the publish-subscribe messaging system.

```yaml
  kafka-service:
    image: bitnami/kafka:latest
    container_name: kafka-container
    ports:
      - "9092:9092"
      - "29092:29092"  # Expose Kafka for external access
    environment:
      KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper-service:2181
      KAFKA_CFG_LISTENERS: PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:29092
      KAFKA_CFG_ADVERTISED_LISTENERS: PLAINTEXT://kafka-service:9092,PLAINTEXT_HOST://localhost:29092
      KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_CFG_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      ALLOW_PLAINTEXT_LISTENER: "yes"
    depends_on:
      - zookeeper-service
```
✅ **Key Points:**
- Uses the `bitnami/kafka` image.
- **Exposes two ports:**
    - `9092:9092` → For **internal communication** (used by Kafdrop & other Docker services).
    - `29092:29092` → For **external communication** (used by Java applications running on the host machine).
- **Listener Configuration:**
    - `PLAINTEXT://kafka-service:9092` → Used for **internal services like Kafdrop**.
    - `PLAINTEXT_HOST://localhost:29092` → Used for **external applications (Java producer/consumer)**.
- **Security Mapping:** Ensures that both listeners use the `PLAINTEXT` security protocol.

---

### 3️⃣ **Kafdrop UI (`kafka-ui-service`)**
Kafdrop is a web-based UI for managing and monitoring Kafka topics, partitions, and messages.

```yaml
  kafka-ui-service:
    image: obsidiandynamics/kafdrop:latest
    container_name: kafdrop-container
    ports:
      - "9000:9000"
    environment:
      KAFKA_BROKERCONNECT: kafka-service:9092
    depends_on:
      - kafka-service
```
✅ **Key Points:**
- Uses the `obsidiandynamics/kafdrop` image.
- Accessible via **http://localhost:9000**.
- Connects to Kafka **inside Docker** using `kafka-service:9092`.

---

## Running the Kafka Cluster

### **Start the Containers**
```sh
docker-compose up -d
```
✅ This will start **Zookeeper, Kafka, and Kafdrop** in detached mode.

### **Verify Kafka is Running**
```sh
docker ps
```
✅ Should show `zookeeper-container`, `kafka-container`, and `kafdrop-container` running.

### **Check Kafka Topics (From Host Machine)**
```sh
kafka-topics.sh --bootstrap-server localhost:29092 --list
```
✅ This should return a list of topics in Kafka.

### **Access Kafdrop UI**
Open **[http://localhost:9000](http://localhost:9000)** in your browser to manage Kafka topics.

### **Stop and Remove Containers**
```sh
docker-compose down -v
```
✅ Stops all services and removes volumes.

---

## **Conclusion**
This setup allows:
✅ **Internal Kafka communication** via `kafka-service:9092` (for Docker services like Kafdrop).
✅ **External access from host** via `localhost:29092` (for Java applications).
✅ **Web-based Kafka management** using Kafdrop UI at `http://localhost:9000`.

This is a simple yet powerful way to run Kafka locally using Docker! 🚀