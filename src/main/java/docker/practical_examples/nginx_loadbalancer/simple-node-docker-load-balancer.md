# Simple Node.js + Docker Project

## Overview

This project contains a simple Node.js application that is containerized
using Docker.

The application is designed to be extended into a load-balancing setup
with:

-   3 Node.js application containers
-   1 Nginx container acting as a reverse proxy and load balancer

### Current Architecture

``` text
Client
  |
  v
Nginx
  |
  +---- app1:3000
  |
  +---- app2:3000
  |
  +---- app3:3000
```

All three Node.js containers use the same Docker image stored in Docker
Hub.

------------------------------------------------------------------------

## 1. Node.js Application

The application is implemented using Express.

The server listens on:

``` text
0.0.0.0:3000
```

`0.0.0.0` allows the Node.js application to accept connections through
the container's network interfaces.

The application exposes two endpoints:

### Root endpoint

``` text
GET /
```

Returns a JSON response containing the application message and container
hostname.

Example:

``` json
{
  "message": "Hello from Node.js application!",
  "hostname": "..."
}
```

The hostname is useful for identifying which container handled a
request.

### Health endpoint

``` text
GET /health
```

Example response:

``` json
{
  "status": "UP",
  "hostname": "..."
}
```

------------------------------------------------------------------------

## 2. Dockerfile

The Node.js application is packaged into a Docker image.

The Dockerfile:

1.  Uses a Node.js Alpine base image.
2.  Sets `/app` as the working directory.
3.  Copies `package.json` and `package-lock.json`.
4.  Installs dependencies.
5.  Copies `server.js`.
6.  Exposes port `3000`.
7.  Starts the application using `node server.js`.

Example:

``` dockerfile
FROM node:22-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY server.js .

EXPOSE 3000

CMD ["node", "server.js"]
```

------------------------------------------------------------------------

## 3. Docker Image

The image was built locally and pushed to Docker Hub.

The image is referenced as:

``` text
<DOCKER_HUB_USERNAME>/simple-node-project:v1
```

This same image is used for all three Node.js application containers.

------------------------------------------------------------------------

## 4. Docker Compose

Docker Compose is used to run the complete multi-container application.

The Compose configuration defines:

``` text
app1
app2
app3
nginx
```

The three application services listen internally on port `3000`.

Nginx listens on port `80`.

Compose also creates a private network so that services can communicate
using their service names:

``` text
app1:3000
app2:3000
app3:3000
```

### Important

`app1`, `app2`, and `app3` are **Compose service names**.

Nginx uses these service names to reach the Node.js containers.

------------------------------------------------------------------------

## 5. Nginx

Nginx acts as both:

-   Reverse proxy
-   Load balancer

The Nginx upstream configuration contains:

``` nginx
upstream backend {
    server app1:3000;
    server app2:3000;
    server app3:3000;
}
```

Requests received by Nginx are forwarded to the Node.js containers.

By default, Nginx uses round-robin distribution between the upstream
servers.

------------------------------------------------------------------------

# Running the Project

From the project directory:

``` powershell
docker compose up -d
```

Check the containers:

``` powershell
docker compose ps
```

You should see four containers:

``` text
app-1
app-2
app-3
nginx-load-balancer
```

------------------------------------------------------------------------

# Accessing the Endpoints

## Through Nginx

The main application endpoint is:

``` text
http://localhost/home
```

Health endpoint:

``` text
http://localhost/health
```

Welcome endpoint:
``` text
http://localhost
```

These requests go through Nginx.

``` text
Browser
   |
   | http://localhost
   v
 Nginx :80
   |
   +---- app1:3000
   +---- app2:3000
   +---- app3:3000
```

Refresh `http://localhost/` multiple times to observe different
container hostnames.

You can also test from PowerShell:

``` powershell
curl http://localhost/
```

and:

``` powershell
curl http://localhost/health
```

------------------------------------------------------------------------

# Viewing Logs

View all Compose logs:

``` powershell
docker compose logs
```

View only Nginx logs:

``` powershell
docker compose logs nginx
```

View a specific Node.js service:

``` powershell
docker compose logs app1
```

or:

``` powershell
docker compose logs app2
docker compose logs app3
```

Follow logs continuously:

``` powershell
docker compose logs -f
```

------------------------------------------------------------------------

# Stopping the Project

Stop the containers:

``` powershell
docker compose down
```

Start them again:

``` powershell
docker compose up -d
```

------------------------------------------------------------------------

# Current Learning Flow

The project was built in this order:

``` text
1. Create Node.js application
        |
2. Run Node.js locally
   node server.js
        |
3. Create Dockerfile
        |
4. Build Docker image
        |
5. Push image to Docker Hub
        |
6. Create Docker Compose configuration
        |
7. Run 3 Node.js containers
        |
8. Add Nginx
        |
9. Use Nginx as reverse proxy + load balancer
```

The final goal is to understand how multiple instances of the same
backend service can be placed behind a single Nginx entry point.
