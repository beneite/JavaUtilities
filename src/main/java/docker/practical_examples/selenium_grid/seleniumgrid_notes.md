## `docker-compose.yml` Explanation:

This Docker Compose file is used to set up a Selenium Grid with ARM64-compatible images on a Mac Silicon (ARM-based) machine. It defines three services:

1. **Selenium Hub (selenium-grid-service)**:
   The hub acts as the central point where test requests are sent. It manages browser nodes (Chrome and Firefox in this case) and routes the test requests to the appropriate node.

    - **Image**: `seleniarm/hub:4.20` — ARM64-compatible Selenium Hub image.
    - **Ports**: Exposes port `4444` on the host, which is the Selenium Grid console where test jobs are received.

2. **Chrome Node (chrome-service)**:
   This service runs Chrome browser instances as part of the grid. The node connects to the Selenium Hub and listens for test requests.

    - **Image**: `seleniarm/node-chromium:4.20` — ARM64-compatible Chrome browser node image.
    - **Environment Variables**:
        - `SE_EVENT_BUS_HOST`: Points to the hub service (`selenium-grid-service`) to let the node register with the hub.
        - `SE_EVENT_BUS_PUBLISH_PORT` and `SE_EVENT_BUS_SUBSCRIBE_PORT`: Used for communication between the node and the hub.

3. **Firefox Node (firefox-service)**:
   This service runs Firefox browser instances as part of the grid. Like the Chrome node, it connects to the hub and listens for test jobs.

    - **Image**: `seleniarm/node-firefox:4.20` — ARM64-compatible Firefox browser node image.
    - **Environment Variables**:
        - `SE_EVENT_BUS_HOST`: Points to the hub service (`selenium-grid-service`).
        - `SE_EVENT_BUS_PUBLISH_PORT` and `SE_EVENT_BUS_SUBSCRIBE_PORT`: Used for communication between the node and the hub.

### `docker-compose.yml`

```yaml
version: "3"
services:
  selenium-grid-service:
    image: seleniarm/hub:4.20
    ports:
      - 4444:4444

  chrome-service:
    image: seleniarm/node-chromium:4.20
    depends_on:
      - selenium-grid-service
    environment:
      - SE_EVENT_BUS_HOST=selenium-grid-service
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  firefox-service:
    image: seleniarm/node-firefox:4.20
    depends_on:
      - selenium-grid-service
    environment:
      - SE_EVENT_BUS_HOST=selenium-grid-service
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
```

---

## `README.md`

```md
# Selenium Grid with ARM64 Docker Setup

This repository provides a Docker Compose setup for running Selenium Grid with Chrome and Firefox browser nodes on ARM64 architecture machines, such as Apple Silicon (M1/M2) devices. The setup uses the **seleniarm** Docker images, which are optimized for ARM64 architecture.

## Services

The `docker-compose.yml` file defines three services:

1. **Selenium Hub (`selenium-grid-service`)**:
   - **Image**: `seleniarm/hub:4.20`
   - This service is the central hub of the Selenium Grid. It receives test requests and routes them to the connected browser nodes (Chrome and Firefox).
   - **Ports**: Exposes port `4444` for accessing the Selenium Grid console at `http://localhost:4444`.

2. **Chrome Node (`chrome-service`)**:
   - **Image**: `seleniarm/node-chromium:4.20`
   - This service runs instances of the Chrome browser for executing test jobs.
   - **Environment**:
     - `SE_EVENT_BUS_HOST`: Hostname for the Selenium Hub (`selenium-grid-service`).
     - `SE_EVENT_BUS_PUBLISH_PORT`: Port used by the node to publish events to the hub.
     - `SE_EVENT_BUS_SUBSCRIBE_PORT`: Port used by the node to subscribe to the hub for receiving commands.

3. **Firefox Node (`firefox-service`)**:
   - **Image**: `seleniarm/node-firefox:4.20`
   - This service runs instances of the Firefox browser for executing test jobs.
   - **Environment**:
     - `SE_EVENT_BUS_HOST`: Hostname for the Selenium Hub (`selenium-grid-service`).
     - `SE_EVENT_BUS_PUBLISH_PORT`: Port used by the node to publish events to the hub.
     - `SE_EVENT_BUS_SUBSCRIBE_PORT`: Port used by the node to subscribe to the hub for receiving commands.

## Getting Started

1. Clone this repository and navigate to the project directory.
2. Make sure Docker is installed on your ARM64 machine (e.g., Mac with Apple Silicon).
3. Run the following command to start the services:
   ```bash
   docker-compose up
   ```
4. Access the Selenium Grid console at:
   ```
   http://localhost:4444
   ```
   From here, you can monitor the registered nodes and run your automated tests.

## Notes

- Ensure your machine supports ARM64 architecture. This setup uses the `seleniarm` Docker images, which are compatible with ARM-based machines (e.g., Apple M1/M2).
- The Chrome and Firefox nodes are set to register automatically with the Selenium Hub once the grid is up and running.

## Troubleshooting

- If you see the message `The Grid has no registered Nodes yet`, check the container logs to ensure the nodes are connecting to the hub.
   ```bash
   docker-compose logs chrome-service
   docker-compose logs firefox-service
   ```
- Ensure the `SE_EVENT_BUS_HOST` in the environment variables for the nodes matches the name of the Selenium Hub service (`selenium-grid-service`).
```

provides an overview of the services, how to get started, and some troubleshooting tips. Let me know if you need further adjustments!