# MySQL and Adminer Docker Compose Setup

This setup defines a MySQL database container with persistent data storage, along with Adminer, a web-based database management tool, using Docker Compose.


## Docker Compose File

```yaml
version: "3"
services:
  mySqlService:
    image: mysql
    environment:
      MYSQL_ROOT_PASSWORD: ashish@123
      MYSQL_USER: ashish
      MYSQL_PASSWORD: ashish@123
      MYSQL_DATABASE: students_db
    ports:
      - 3307:3306
    volumes:
      - ./init_script:/docker-entrypoint-initdb.d   # For initialization scripts
      - ./mysql_data:/var/lib/mysql                 # For persistent data storage
  adminer-service:
    image: adminer
    ports:
      - 8070:8080
```

### Services Explained

#### 1. MySQL Service (`mySqlService`)

- **Image**: Uses the official MySQL Docker image.

- **Environment Variables**:
    - `MYSQL_ROOT_PASSWORD`: Root password for the MySQL database (`ashish@123`).
    - `MYSQL_USER`: Non-root MySQL user (`ashish`).
    - `MYSQL_PASSWORD`: Password for the non-root user (`ashish@123`).
    - `MYSQL_DATABASE`: The database to be created on startup (`students_db`).

- **Ports**:
    - Maps MySQL’s internal port `3306` to `3307` on the host machine.
    - MySQL can be accessed via `localhost:3307`.

- **Volumes**:
    - `./init_script:/docker-entrypoint-initdb.d`: Mounts the local `init_script` directory to `/docker-entrypoint-initdb.d` inside the container. SQL scripts placed here will run on container initialization.
    - `./mysql_data:/var/lib/mysql`: Mounts the local `mysql_data` directory to `/var/lib/mysql` inside the container, ensuring persistent storage for MySQL data.

#### 2. Adminer Service (`adminer-service`)

- **Image**: Uses the official Adminer Docker image.

- **Ports**:
    - Maps Adminer's internal port `8080` to `8070` on the host machine.
    - Adminer can be accessed via `http://localhost:8070` to manage MySQL databases.

## Usage

### 1. Build and Start the Containers

Run the following command to build and start the containers:

```bash
docker-compose up --build
```

### 2. Access MySQL

You can connect to MySQL on `localhost:3307` using any MySQL client. For example, using the `mysql` command line:

```bash
mysql -u ashish -p -h 127.0.0.1 -P 3307
```

### 3. Access Adminer

Adminer can be accessed by navigating to `http://localhost:8070` in your web browser.

Use the following credentials to log in:
- **System**: MySQL
- **Server**: `mySqlService` or `localhost`
- **Username**: `ashish`
- **Password**: `ashish@123`
- **Database**: `students_db`

### 4. Data Persistence

- All MySQL data is stored persistently in the `./mysql_data` directory on your host machine.
- If the container is stopped or removed, the data remains available and will be loaded when a new container is started.

### 5. Initialization Scripts

Any SQL scripts placed in the `./init_script` directory will be automatically executed when the container is first started. This is useful for creating databases, tables, and pre-populating data.

## Stopping the Containers

To stop and remove the containers, run:

```bash
docker-compose down
```