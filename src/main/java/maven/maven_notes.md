# 📘 Apache Maven – Complete Interview Reference

## 1. 🔹 Introduction

* **Maven** = Build automation + Dependency management tool.
* Written in Java, primarily used for Java projects.
* Uses **Project Object Model (POM)** (`pom.xml`) to manage project configuration.
* Provides **standardization**, **convention over configuration**, and **reproducible builds**.

---

## 2. 🔹 Key Features

* Dependency Management (automatic download from repositories).
* Lifecycle Management (compile, test, package, deploy).
* Plugin-based (plugins add tasks like compiling, testing, packaging).
* Supports multi-module projects.
* Integration with CI/CD (Jenkins, GitLab, GitHub Actions).
* Repository system (Local → Central → Remote).

---

## 3. 🔹 Maven Architecture

```
Project → POM.xml → Build Lifecycle → Plugins → Goals → Output (jar/war/ear)
```

1. **Local Repository**: `~/.m2/repository` (cache of dependencies).
2. **Central Repository**: Default Maven repo (`repo.maven.apache.org`).
3. **Remote Repository**: Company/private repos (e.g., Nexus, Artifactory).

---

## 4. 🔹 POM (Project Object Model)

Main configuration file: `pom.xml`

### Structure

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.company</groupId>
    <artifactId>employee-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>Employee Management App</name>
    <description>Spring Boot app for employee management</description>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Example: Spring Boot Starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.3.2</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Compiler plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 5. 🔹 Build Lifecycles

Maven defines 3 lifecycles:

### a) **Default lifecycle** (build)

1. **validate** → Check POM correctness
2. **compile** → Compile source
3. **test** → Run unit tests
4. **package** → Create JAR/WAR
5. **verify** → Integration tests
6. **install** → Copy artifact to local repo
7. **deploy** → Push artifact to remote repo

### b) **Clean lifecycle**

* `pre-clean` → tasks before cleaning
* `clean` → remove `target/`
* `post-clean`

### c) **Site lifecycle**

* `site` → Generate documentation
* `site-deploy` → Publish docs

---

## 6. 🔹 Maven Commands

| Command       | Purpose                         |
| ------------- | ------------------------------- |
| `mvn clean`   | Deletes `target/` directory     |
| `mvn compile` | Compiles source                 |
| `mvn test`    | Runs unit tests                 |
| `mvn package` | Builds jar/war                  |
| `mvn install` | Installs artifact in local repo |
| `mvn deploy`  | Deploys to remote repo          |
| `mvn verify`  | Run checks before install       |
| `mvn site`    | Generates site docs             |

👉 Combination examples:

* `mvn clean install` → clean + compile + test + package + install
* `mvn test package` → compile + test + package
* `mvn dependency:tree` → Shows dependency hierarchy

---

## 7. 🔹 Dependency Management

### Adding dependency:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>
```

### Dependency Scopes

* **compile** (default) – Available everywhere.
* **provided** – Needed for compile, but container provides (e.g., Servlet API).
* **runtime** – Not for compile, only runtime.
* **test** – Only for testing (JUnit, Mockito).
* **system** – External, provided explicitly.
* **import** – Used for dependency management.

### Dependency Conflict Resolution

* **Nearest-Wins Strategy** → If multiple versions exist, the nearest one in dependency tree is picked.

---

## 8. 🔹 Maven Plugins

Plugins provide tasks (goals).

Examples:

* **Compiler Plugin** – `maven-compiler-plugin`
* **Surefire Plugin** – Run unit tests
* **Failsafe Plugin** – Run integration tests
* **Shade Plugin** – Create uber/fat JAR
* **JAR/WAR Plugin** – Package into JAR/WAR
* **Checkstyle Plugin** – Code quality checks

---

## 9. 🔹 Maven Profiles

Profiles allow **different builds for different environments**.

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <db.url>jdbc:mysql://localhost/devdb</db.url>
        </properties>
    </profile>

    <profile>
        <id>prod</id>
        <properties>
            <db.url>jdbc:mysql://prod-server/proddb</db.url>
        </properties>
    </profile>
</profiles>
```

Run with:

```sh
mvn clean install -Pdev
mvn clean install -Pprod
```

---

## 10. 🔹 Multi-Module Projects

Parent `pom.xml` manages multiple modules.

**Parent POM (`pom.xml`)**

```xml
<modules>
    <module>service-api</module>
    <module>service-impl</module>
</modules>
```

**Child module inherits from parent:**

```xml
<parent>
    <groupId>com.company</groupId>
    <artifactId>project-parent</artifactId>
    <version>1.0.0</version>
</parent>
```

---

## 11. 🔹 Maven Best Practices

✅ Keep dependencies minimal.
✅ Use dependency management in parent POM.
✅ Use `mvn dependency:tree` to avoid conflicts.
✅ Separate test/integration test using Surefire & Failsafe.
✅ Use profiles for environment-specific configs.
✅ Prefer company Nexus/Artifactory instead of central repo for consistency.

---

## 12. 🔹 Maven vs Gradle vs Ant

| Feature         | Maven             | Gradle                      | Ant              |
| --------------- | ----------------- | --------------------------- | ---------------- |
| Model           | Declarative (XML) | DSL (Groovy/Kotlin)         | Procedural       |
| Performance     | Slower            | Faster (incremental builds) | Fast but manual  |
| Dependency Mgmt | Built-in          | Built-in                    | Manual           |
| Learning Curve  | Moderate          | Steeper                     | Easy but verbose |

---

## 13. 🔹 Common Interview Questions

1. What is Maven and why use it?
2. Explain Maven lifecycle phases.
3. Difference between `mvn install` and `mvn package`.
4. How does Maven resolve dependency conflicts?
5. What is the difference between Surefire and Failsafe plugins?
6. Explain dependency scopes.
7. What’s the role of parent POM in multi-module projects?
8. How do you create environment-specific builds?
9. Difference between Maven and Gradle?
10. What happens when you run `mvn clean install`?

---

✅ This documentation gives you **both theory and examples** so you can quickly revise before your interview.

Would you like me to also make a **1-page cheat sheet (summary version with diagrams + commands)** that you can keep handy for quick last-minute revision?
