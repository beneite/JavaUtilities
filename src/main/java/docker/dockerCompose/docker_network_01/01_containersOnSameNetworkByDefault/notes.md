## This Docker Compose file defines **two services**: `nginx-container` and `alpine-container`.

---

### 🔍 Full Breakdown

```yaml
version: "3"

services:
  nginx-container:
    image: nginx
```

* This creates a container named `nginx-container` using the official **Nginx** image.
* It will run the default `nginx` web server on port 80 **inside** the container (but no ports are exposed to the host since no `ports:` are defined).

---

```yaml
  alpine-container:
    image: alpine
    depends_on:
      - nginx-container
    entrypoint: "ping nginx-container"
```

* This creates another container using the lightweight **Alpine Linux** image.

* `depends_on: - nginx-container` ensures that Docker **starts `nginx-container` before** this one.
  ⚠️ But it **does not** wait for nginx to be fully ready — only that the container has started.

* `entrypoint: "ping nginx-container"` overrides Alpine’s default behavior and instead **runs the `ping nginx-container` command**.

---

### ✅ What will happen?

When you run:

```bash
docker-compose up
```

Docker will:

1. Start the `nginx-container` (Nginx server).
2. Start the `alpine-container` and execute:

   ```bash
   ping nginx-container
   ```

   This works because:

    * Both containers are in the **same default Docker network** (a custom bridge).
    * Docker DNS allows container name resolution, so `nginx-container` is reachable by that name.

So, you will see continuous ping output from `alpine-container` to `nginx-container`, like:

```
PING nginx-container (172.x.x.x): 56 data bytes
64 bytes from 172.x.x.x: seq=0 ttl=64 time=0.123 ms
...
```

---

### 🧠 Summary

* Creates two containers.
* `nginx-container` runs Nginx.
* `alpine-container` pings the Nginx container using Docker's internal DNS.
* Useful for testing container-to-container networking.
