## What this Compose file does:

1. **Defines two services:**

    * `nginx-container` running the official **nginx** image.
    * `alpine-container` running the lightweight **alpine** image.

2. **Both services are connected to the same user-defined network: `ashish-network`.**

3. The `alpine-container` waits for `nginx-container` to start (via `depends_on`).

4. The `alpine-container` runs the command `ping nginx-container` — it tries to ping the `nginx-container` **by its service name**, which works because they're on the same custom network.

5. **The network `ashish-network` is explicitly created with the `bridge` driver**, which means it is a user-defined bridge network with better container-to-container communication features than the default bridge.

---

### Why `driver: bridge`?

* It creates a **virtual LAN (Local Area Network)** on your Docker host.
* Containers on this network can communicate using **container names** (DNS resolution).
* Provides **isolation** from other Docker networks.
* Supports customization (IP range, subnet, etc. if needed).

---

### What happens when you run this?

* Docker creates `ashish-network` as a bridge network.
* Both containers join this network.
* The `alpine-container` can ping `nginx-container` using its service name, thanks to Docker DNS on the user-defined bridge network.
* Network communication is isolated to only containers connected to `ashish-network`.

---

### Summary

| Key concept            | Your compose file details                                        |
| ---------------------- | ---------------------------------------------------------------- |
| Network name           | `ashish-network`                                                 |
| Network driver         | `bridge` (user-defined bridge network)                           |
| Container connectivity | Containers can reach each other by name                          |
| Isolation              | Containers outside `ashish-network` can't reach these containers |

---

## Explanation:

When you run Docker Compose, it automatically **prefixes your network (and container) names** with the **project name** to avoid name collisions if you have multiple Compose projects on the same Docker host.

* **Project name:** By default, Docker Compose uses the **folder name** where your `docker-compose.yaml` file lives as the project name.
* The network name becomes:

  ```
  <project_name>_<network_name>
  ```
* In your case:

    * Project name is probably: `02_containeroncustomnetwork`
    * Network name you defined: `ashish-network`
    * Resulting network name: `02_containeroncustomnetwork_ashish-network`

---

