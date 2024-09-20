FROM alpine
RUN apk add openjdk8
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/bin/javac
WORKDIR /ashish
ADD ParameterToContainerProgram.java ParameterToContainerProgram.java
ENTRYPOINT javac ParameterToContainerProgram.java && java ParameterToContainerProgram $NUM