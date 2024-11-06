## Docker Commands

#### 1. **Basic Commands**
- **Run a Container**: `docker run [OPTIONS] IMAGE [COMMAND]`
    - Example: `docker run --name my-container -d nginx`
    - `-d`: Run container in detached mode (in the background).
    - `--name`: Name the container for easier reference.

- **List Containers**:
    - Running containers: `docker ps`
    - All containers (including stopped ones): `docker ps -a`

- **Start/Stop a Container**:
    - Start: `docker start CONTAINER_ID`
    - Stop: `docker stop CONTAINER_ID`
    - Restart: `docker restart CONTAINER_ID`

- **Remove a Container**: `docker rm CONTAINER_ID`
    - Use `docker rm -f CONTAINER_ID` to force-remove a running container.

- **Remove an Image**: `docker rmi IMAGE_ID`
    - Use `docker rmi -f IMAGE_ID` to force-remove an image.

- **Execute a Command Inside a Running Container**: `docker exec -it CONTAINER_ID COMMAND`
    - Example: `docker exec -it my-container /bin/bash` (access a shell in the container).

---

#### 2. **Image Management**
- **Build an Image**: `docker build -t IMAGE_NAME PATH`
    - Example: `docker build -t my-app .` (builds an image from the Dockerfile in the current directory).
    - `-t`: Tag an image with a name (optional).

- **Pull an Image**: `docker pull IMAGE`
    - Example: `docker pull nginx` (downloads the latest nginx image from Docker Hub).

- **List Images**: `docker images`
    - Shows all images on your system, with details like tag and size.

- **Tag an Image**: `docker tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]`
    - Example: `docker tag my-app:latest my-repo/my-app:v1.0`

- **Push an Image to a Repository**: `docker push IMAGE`
    - Example: `docker push my-repo/my-app:v1.0`

---

#### 3. **Networking**
- **Expose Ports**: Use `-p` or `--publish` when running a container to map a host port to a container port.
    - Example: `docker run -d -p 8080:80 nginx`
        - Maps port `8080` on the host to port `80` in the container.

- **List Networks**: `docker network ls`
    - Shows all Docker networks on the host.

- **Create a Network**: `docker network create NETWORK_NAME`
    - Example: `docker network create my-network`

- **Connect a Container to a Network**: `docker network connect NETWORK_NAME CONTAINER_NAME`
    - Example: `docker network connect my-network my-container`

- **Disconnect a Container from a Network**: `docker network disconnect NETWORK_NAME CONTAINER_NAME`

---

#### 4. **Volumes and Storage**
- **Create a Volume**: `docker volume create VOLUME_NAME`
    - Example: `docker volume create my-volume`

- **List Volumes**: `docker volume ls`

- **Mount a Volume**: Use `-v` or `--mount` to attach a volume to a container.
    - `docker run -d -v VOLUME_NAME:/path/in/container IMAGE`
    - Example: `docker run -d -v my-volume:/data nginx`

- **Remove a Volume**: `docker volume rm VOLUME_NAME`

---

#### 5. **Inspecting and Monitoring**
- **Inspect a Container or Image**: `docker inspect CONTAINER_ID` or `docker inspect IMAGE_ID`
    - Provides detailed configuration and status information.

- **Container Logs**: `docker logs CONTAINER_ID`
    - Example: `docker logs -f my-container` (follows logs in real-time).

- **Container Resource Usage**: `docker stats`
    - Shows CPU, memory, and I/O usage of running containers.

---

#### 6. **Docker Compose (for Multi-Container Applications)**
- **Start Containers**: `docker-compose up`
    - Use `-d` to run in detached mode: `docker-compose up -d`

- **Stop Containers**: `docker-compose down`

- **Rebuild Images**: `docker-compose build`
    - Rebuild images if the Dockerfile or dependencies have changed.

- **View Logs**: `docker-compose logs`
    - Use `-f` for real-time logs: `docker-compose logs -f`

--- 

These commands should cover a wide range of Docker tasks, from setting up containers and managing images to handling networks and storage. Let me know if you need more details on any specific command!