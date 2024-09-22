# Objective:
- over here we are trying to create 2 container (nginx-container & alpine-container) and we will be demonstrating the volume mapping by downloading the content and putting git into out local.

# Docker Compose Example: NGINX and Alpine with `wget`

This Docker Compose setup defines two services:
1. **`nginx-container`**: Runs an NGINX web server.
2. **`alpine-container`**: Uses the Alpine Linux image to run the `wget` command and download content from the `nginx-container`.

## Docker Compose Version

The Compose file uses **version 3** of the Docker Compose file format.

```yaml
version: "3"
```

## Services

### 1. `nginx-container`

This service creates a container using the official NGINX image, which sets up a basic web server.

```yaml
nginx-container:
  image: nginx
```

- **`image: nginx`**: The container uses the official NGINX image from Docker Hub.
- NGINX will serve content, which the other container (`alpine-container`) can download using the `wget` command.
- The service is accessible via the name `nginx-container` on the internal Docker network, allowing other services in the same network to communicate with it.

### 2. `alpine-container`

This service creates a lightweight Alpine Linux container that uses the `wget` command to download content from the `nginx-container`.

```yaml
alpine-container:
  image: alpine
  depends_on:
    - nginx-container
  working_dir: /fileDownload
  entrypoint: "wget http://nginx-container"
  volumes:
    - ./fileSyncFolder:/fileDownload
```

- **`image: alpine`**: Uses the official Alpine Linux image, a minimal base image.
- **`depends_on`**: Ensures that the `nginx-container` starts first. However, it doesn't guarantee that NGINX will be fully initialized and serving content before `alpine-container` starts.
- **`working_dir: /fileDownload`**: Sets the working directory inside the `alpine-container` to `/fileDownload`, where files will be downloaded.
- **`entrypoint: "wget http://nginx-container"`**: The container runs the `wget` command to download content from the `nginx-container`. The service name `nginx-container` is used to resolve the container's address on the internal Docker network.
- **`volumes: ./fileSyncFolder:/fileDownload`**: Maps the local directory `./fileSyncFolder` to `/fileDownload` inside the container. This allows files downloaded by `wget` to be available on your local machine in the `fileSyncFolder`.

## How the Setup Works

1. **Start the containers**: When you run `docker-compose up`, Docker Compose launches both the `nginx-container` and `alpine-container`. The `alpine-container` waits for the `nginx-container` to start because of the `depends_on` setting.

2. **Communication between containers**: The `alpine-container` tries to download content from the `nginx-container` using the `wget` command (`wget http://nginx-container`). Docker Compose automatically configures a network where containers can communicate using their service names as hostnames.

3. **File Syncing**: The downloaded content will be saved inside `/fileDownload` within the `alpine-container`. This folder is mapped to your local folder `./fileSyncFolder` using a volume, so you can access the downloaded files directly from your local machine.

## Running the Project

### 1. Start the Services

Use the following command to start the services:

```bash
docker-compose up
```

This will:
- Start the `nginx-container` (running an NGINX web server).
- Start the `alpine-container`, which will use `wget` to download content from the `nginx-container`.

### 2. Check the Downloaded Files

Once the containers are up and running, any content downloaded by the `alpine-container` using `wget` will be stored in the `./fileSyncFolder` directory on your local machine.

### 3. Stop the Services

When you're done, you can stop and remove the containers with:

```bash
docker-compose down
```

This will stop both the `nginx-container` and the `alpine-container` and clean up the containers.

## File Structure

Here’s an example of what your file structure might look like:

```
.
├── docker-compose.yml        # Defines the NGINX and Alpine services
├── fileSyncFolder/           # Local folder to sync downloaded files from /fileDownload in the alpine container
└── README.md                 # Documentation explaining the setup
```

## Key Concepts

- **Docker Networking**: Docker Compose automatically creates a network for services to communicate. Here, `alpine-container` can communicate with `nginx-container` using the service name `nginx-container` as the hostname.
- **Volumes**: The volume defined in the `alpine-container` (`./fileSyncFolder:/fileDownload`) ensures that files downloaded in the container are also available on your local filesystem.
- **`depends_on`**: This ensures the `alpine-container` starts after the `nginx-container`. However, note that this doesn't wait for the NGINX service to be fully initialized, just that the container is started.