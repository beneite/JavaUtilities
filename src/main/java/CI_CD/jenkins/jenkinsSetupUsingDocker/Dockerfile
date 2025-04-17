FROM jenkins/jenkins:lts

USER root

# Install required packages
RUN apt-get update && apt-get install -y curl tar

# Install Maven 3.9.6 manually
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz | tar -xz -C /opt && \
    ln -s /opt/apache-maven-3.9.6 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /opt/maven
ENV PATH $PATH:$MAVEN_HOME/bin

USER jenkins
