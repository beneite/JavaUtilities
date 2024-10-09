### In FTP, the **control** and **data** connections are handled separately, and the distinction between **active** and **passive** FTP modes relates to how these connections are established.

### 1. **FTP Control Port (Port 21)**:
- This port is used to send **commands** between the client and the server, such as login credentials, directory navigation, and file transfer requests.
- The control connection is always initiated by the client and maintained throughout the FTP session. It uses **port 21** by default.
- Example commands: `USER`, `PASS`, `PWD`, `LIST`, etc.

### 2. **Active FTP Mode (Data Port 20)**:
- In **active FTP mode**, after the client initiates a control connection (on port 21), the **server** opens a new connection to the client for data transfer.
- The server uses its **port 20** (the active FTP **data port**) to initiate the data connection back to the client's randomly selected port (often greater than 1023).
- Active mode may face issues when the client is behind a firewall or NAT because the server needs to connect to the client’s network.

**How it works:**
- The client tells the server what port it’s listening on using the `PORT` command.
- The server then initiates the data connection to the client's specified port, using its port 20.

### 3. **Passive FTP Mode (Data Ports)**:
- In **passive FTP mode**, the client is responsible for establishing both the control and the data connections.
- After the client connects on the control port (21), the server opens a random port (from a predefined range, e.g., `21100-21110`) and tells the client to connect to that port for data transfer (using the `PASV` command).
- Passive mode is often preferred because the client initiates all connections, avoiding issues with firewalls and NAT.

**How it works:**
- The client sends a `PASV` command to the server.
- The server responds with the IP address and port number (from the passive mode range) where it’s listening for the data connection.
- The client then initiates the data connection to that IP and port.

### Summary:
- **Control Port (21)**: Handles all commands and controls the FTP session.
- **Active FTP Mode**: The server opens a data connection to the client from port 20.
- **Passive FTP Mode**: The client opens the data connection to a server-specified port (e.g., from a range like 21100-21110).

Since passive mode solves firewall/NAT issues by allowing the client to initiate all connections, it’s commonly used in modern FTP setups.