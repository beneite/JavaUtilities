# Docker Network Example

Here we will be creating a network and on that same network, we will create two containers. We will then attempt to ping the Nginx container from the Alpine container.

### Steps

1. **Create a network:**

    ```bash
    docker network create Ashish_network
    ```

   Output:

    ```
    664cd332e34ee388ba54aa740f66ba2e11edd5a0028d4debdf0a66c620fcd6e2
    ```

2. **Run the Nginx container on the created network:**

    ```bash
    docker run -d --network=Ashish_network --name=nginx_container nginx
    ```

   Output:

    ```
    88378e24d05e48e0a395336fc3b403324b73c11cc5a5f8cdb09abe58d78b6d7d
    ```

3. **Run the Alpine container on the same network and ping the Nginx container:**

    ```bash
    docker run -it --network=Ashish_network alpine
    ```

   Inside the Alpine container, ping Nginx:

    ```bash
    / # ping nginx_container
    ```

   Output:

    ```
    PING nginx_container (172.18.0.2): 56 data bytes
    64 bytes from 172.18.0.2: seq=0 ttl=64 time=0.210 ms
    ```

---

### Note: Docker Compose and Default Networks

In Docker Compose, when you define multiple services (like `nginx-container` and `alpine-container` in your YAML file) **without specifying a network**, Docker Compose automatically creates a default network for all the services in the same project. This means the services can communicate with each other by their service names, even without explicitly defining a network.

### Potential Problem:

- If Alpine goes online first, it will start pinging Nginx, and since Nginx may not be online yet, the ping will fail. To fix this, use the `depends_on` feature in Docker Compose.

Example usage in Docker Compose:
```yaml
services:
  nginx-container:
    image: nginx

  alpine-container:
    image: alpine
    depends_on:
      - nginx-container
```
# More on Docker network
> In Docker, a **network** is a way to allow containers to communicate with each other, the host, or the internet. Docker provides several built-in networking options to support different use cases.

### 🔧 Types of Docker Networks

| Network Type      | Description                                                                                |
| ----------------- | ------------------------------------------------------------------------------------------ |
| **bridge**        | Default network for containers. Best for standalone containers on a single host.           |
| **host**          | Shares the host’s network stack. No isolation. Only available on Linux.                    |
| **none**          | Container is isolated from all networks. Useful for security.                              |
| **overlay**       | Enables multi-host communication using Docker Swarm.                                       |
| **macvlan**       | Assigns a MAC address to container, making it appear as a physical device on the network.  |
| **custom bridge** | User-defined bridge network. Containers can communicate using names (DNS-based discovery). |

---

### 🛠 Common Docker Network Commands

```bash
# List all networks
docker network ls

# Create a custom bridge network
docker network create my-network

# Run container in a specific network
docker run -d --name container1 --network my-network nginx

# Connect a running container to a network
docker network connect my-network container2

# Disconnect a container from a network
docker network disconnect my-network container2

# Inspect network details
docker network inspect my-network

# Remove a network
docker network rm my-network
```

---

### 💡 Notes

* Containers on the **same custom bridge** can communicate by container name (DNS).
* **Default `bridge`** network does not support container name resolution — use `--link` or better, a custom bridge.
* Use **overlay networks** for **Docker Swarm** or multi-host setups.

---
