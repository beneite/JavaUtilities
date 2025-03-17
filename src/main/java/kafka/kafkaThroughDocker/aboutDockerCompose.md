This `docker-compose.yml` file defines two services, **Zookeeper** and **Kafka**, that are essential for running an Apache Kafka setup using Docker.

---

## **Service Descriptions**

### **1. Zookeeper Service (`zookeeper-service`)**
- **Image:** `zookeeper:3.9.3-jre-17`
    - Uses the official **Zookeeper** Docker image with **Java 17**.
- **Container Name:** `zookeeper-container`
- **Ports:**
    - Maps **Zookeeper's** default port `2181` from the container to the host (`2181:2181`).
- **Profiles:**
    - Belongs to the **"kafka"** profile, meaning it will only start when the `kafka` profile is used.

🔹 **Zookeeper** is a critical dependency for Kafka, managing broker coordination, leader election, and maintaining metadata.

---

### **2. Kafka Service (`kafka-service`)**
- **Image:** `bitnami/kafka:latest`
    - Uses the **Bitnami Kafka** image, which simplifies configuration.
- **Container Name:** `kafka-container`
- **Ports:**
    - Exposes Kafka’s default broker port (`9092:9092`).
- **Environment Variables:**
    - `KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper-service:2181`
        - Configures Kafka to connect to the **Zookeeper service** running at `zookeeper-service:2181`.
    - `KAFKA_CFG_LISTENERS: PLAINTEXT://:9092`
        - Defines a **PLAINTEXT** listener on port `9092`.
    - `KAFKA_CFG_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092`
        - Kafka will advertise itself as `localhost:9092`, ensuring clients can connect.
    - `KAFKA_CFG_BROKER_ID: 1`
        - Sets the **Kafka Broker ID** (should be unique in a multi-node cluster).
    - `ALLOW_PLAINTEXT_LISTENER: "yes"`
        - Allows Kafka to accept plaintext connections (useful for local development).
- **Depends On:**
    - `zookeeper-service`: Ensures that **Zookeeper** starts before Kafka.
- **Profiles:**
    - Belongs to the **"kafka"** profile.

🔹 **Kafka** relies on **Zookeeper** to manage its brokers and distribute messages reliably.

---

## **How to Use This Setup?**
### **Starting Kafka & Zookeeper**
Run the following command to start both services using the `kafka` profile:
```sh
docker-compose --profile kafka up -d
```
This will start **Zookeeper** and **Kafka** in detached mode (`-d` runs them in the background).

### **Stopping Kafka & Zookeeper**
```sh
docker-compose --profile kafka down
```
This stops and removes the containers.
