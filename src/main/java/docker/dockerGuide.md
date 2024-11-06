![dockervsVm.png](resources%2Fgitresources%2FdockervsVm.png)

> [!NOTE]
> For Docker Commands click [here](commands/dockerCommands.md)

## What is Docker?

>Docker is an open-source platform that automates the deployment, scaling, and management of applications within lightweight, portable containers. Containers are standardized, executable units that include everything the application needs to run, from code and runtime to system libraries and dependencies.

>Think of Docker as a way to package and run applications in isolated environments, regardless of the host system. This consistency eliminates issues caused by differences in development, staging, and production environments.

### Key Concepts in Docker

1. **_Container_**: A lightweight, standalone, and executable package that includes everything needed to run a piece of software, including code, runtime, libraries, and system settings. Containers can run isolated from each other and the host system.

2. **_Image_**: A blueprint for a container, containing the application and its dependencies. An image is immutable and is built from a series of layers, each representing a change or addition to the base image. Containers are instances of images.

3. **_Docker Engine_**: The core part of Docker, it’s the runtime responsible for building, running, and managing containers. The Docker Engine allows you to run Docker on different operating systems.

4. **_Dockerfile_**: A text file containing a set of instructions on how to build a Docker image. Each line in a Dockerfile is an instruction for creating a layer in the final image.

5. **_Docker Hub_**: A public repository where users can share Docker images. You can pull official or community images from Docker Hub to get started quickly.

#### Why Use Docker?

- **Portability**: Docker containers can run on any system with Docker installed, making it easy to move applications between development, test, and production environments.
- **Consistency**: By packaging applications and dependencies together, Docker eliminates "works on my machine" issues.
- **Efficiency**: Containers share the host OS kernel, so they use fewer resources than virtual machines (VMs).
- **Scalability**: Docker makes it simple to scale applications horizontally by adding or removing containers as needed.
- **Isolation**: Each container runs in its isolated environment, meaning that changes in one container won’t affect others.

Docker has revolutionized the way applications are developed, tested, and deployed by enabling a faster and more consistent development pipeline.