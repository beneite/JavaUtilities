This updated Docker Compose file creates two services: a PostgreSQL database (`postgres-container`) and an Adminer web interface (`adminer-container`). Here's a detailed breakdown of the configuration:

### 1. **PostgreSQL Service (`postgres-container`)**:
- **Image**: It uses the official `postgres` image.
- **Environment**:
    - `POSTGRES_USER: ashish`: The username for the PostgreSQL database is set to `ashish`.
    - `POSTGRES_PASSWORD: ashish@123`: The password is set to `ashish@123`.
    - `POSTGRES_DB: students_db`: A database called `students_db` will be created upon initialization.
- **Volumes**:
    - `./init_script:/docker-entrypoint-initdb.d`: This maps the local `init_script` directory to `/docker-entrypoint-initdb.d` in the container. Any SQL or shell scripts placed in this directory will be automatically executed when the PostgreSQL container is initialized. You can use this to seed your database or run any setup tasks.

### 2. **Adminer Service (`adminer-container`)**:
- **Image**: The `adminer` image is used to provide a simple web interface for managing the PostgreSQL database.
- **Ports**:
    - `8080:8080`: This maps port 8080 on the host to port 8080 on the container, allowing you to access Adminer at `http://localhost:8080`.

### To run this setup:
1. Save the configuration in a `docker-compose.yml` file.
2. Run the following command to start the services:
   ```bash
   docker-compose up -d
   ```

### Accessing Adminer:
- **URL**: `http://localhost:8080`
- **System**: PostgreSQL
- **Server**: `postgres-container` (or `localhost` if networking is bridged)
- **Username**: `ashish`
- **Password**: `ashish@123`
- **Database**: `students_db`

The `init_script` directory can contain `.sql` files to initialize or seed the database automatically when the container starts.