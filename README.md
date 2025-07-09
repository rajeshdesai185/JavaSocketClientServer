# Java Socket Programming – Client-Server Architecture

This project demonstrates two Java-based implementations of a TCP client-server system:

- **Single-threaded server**: Handles one client connection at a time.
- **Multithreaded server**: Handles multiple clients simultaneously using threads.

---

## 📁 Contents

| Folder / File                          | Description                                                                                       |
|----------------------------------------|---------------------------------------------------------------------------------------------------|
| `single_threaded/`                     | Java code for a basic client-server setup with one client handled at a time.                      |
| `multithreaded/`                       | Java code for a threaded server that handles multiple clients concurrently.                       |
| `README.md`                            | Main project documentation including setup, usage, and description.                               |

---

## ⚙️ How to Run

### Requirements
- Java JDK 8 or higher
- Command line or IDE (e.g. VS Code)

### Steps

1. Navigate to the desired version (`single_threaded/` or `multithreaded/`)
2. Compile the source files:
   ```bash
   javac Server.java
   javac Client.java

### 1. Single-threaded Server
         +------------+        TCP        +------------------+
         |   Client   |  <--------------> |   ServerSocket   |
         +------------+                  +------------------+
                                          | Accepts one client
                                          | Handles request
                                          | Sends response
                                          +------------------

### 2. Multithreaded Server
   
         +------------+                   +----------------------+
         |  Client 1  |   TCP Connection  |                      |
         +------------+  <------------->  |                      |
         +------------+                   |                      |
         |  Client 2  |   TCP Connection  |   ServerSocket       |
         +------------+  <------------->  |     (Main Thread)    |
         +------------+                   |                      |
         |  Client N  |   TCP Connection  |                      |
         +------------+  <------------->  |                      |
                                         +-----------+----------+
                                                     |
                                                     | (new Thread per client)
                                                     ↓
                                          +------------------------+
                                          |   ClientHandler Thread |
                                          |   Handles one client   |
                                          +------------------------+



