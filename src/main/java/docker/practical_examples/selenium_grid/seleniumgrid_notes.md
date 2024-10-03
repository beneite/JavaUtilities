## `docker-compose.yml` Explanation:

This Docker Compose file is setting up a Selenium Grid with ARM64-compatible images on an ARM-based machine (such as Apple Silicon). The grid consists of three services:

1. **Selenium Hub (selenium-grid-service):**
   - **Image**: `seleniarm/hub:4.20` — The ARM64-compatible image for the Selenium Hub.
   - **Ports**:
      - Exposes port `4444` to the host machine, which allows access to the Selenium Grid web interface and to route test commands.
   - **Purpose**: This service acts as the hub that coordinates test executions across the browser nodes (Chrome and Firefox). It receives test requests and routes them to the appropriate browser node.

2. **Chrome Node (chrome-service):**
   - **Image**: `seleniarm/node-chromium:4.20` — ARM64-compatible image for the Selenium Chrome browser node.
   - **`shm_size`**:
      - `shm_size: '2g'` increases the size of the shared memory (from the default `64MB` to `2GB`), which is important for browser stability. This helps to prevent browser crashes when dealing with heavy web pages or resource-intensive operations.
   - **Depends on**:
      - The `selenium-grid-service` must be up and running before this service starts.
   - **Environment Variables**:
      - `SE_EVENT_BUS_HOST`: Refers to the Selenium Hub (`selenium-grid-service`), so the node knows where to connect.
      - `SE_EVENT_BUS_PUBLISH_PORT` and `SE_EVENT_BUS_SUBSCRIBE_PORT`: These ports are used to establish communication between the Chrome node and the Selenium Hub for event publishing and subscribing.

3. **Firefox Node (firefox-service):**
   - **Image**: `seleniarm/node-firefox:4.20` — ARM64-compatible image for the Selenium Firefox browser node.
   - **`shm_size`**:
      - `shm_size: '2g'` increases the shared memory allocation to prevent crashes during resource-heavy operations.
   - **Depends on**:
      - Like the Chrome service, the Firefox node also depends on the Selenium Hub being up and running first.
   - **Environment Variables**:
      - `SE_EVENT_BUS_HOST`: Points to the Selenium Hub service (`selenium-grid-service`) for node registration.
      - `SE_EVENT_BUS_PUBLISH_PORT` and `SE_EVENT_BUS_SUBSCRIBE_PORT`: Used to communicate between the Firefox node and the Selenium Hub.

### Why Use `shm_size`?

Increased shared memory (`/dev/shm`) is necessary when running browsers like Chrome or Firefox in Docker containers. Browsers rely heavily on shared memory for inter-process communication and rendering web pages. If the memory is insufficient, it can lead to browser crashes, especially on resource-heavy websites or during complex test scenarios.

- By setting `shm_size: '2g'`, you're giving the container more memory (2GB) to ensure smooth browser operation and avoid out-of-memory crashes.

---