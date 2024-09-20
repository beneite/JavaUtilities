FROM alpine
RUN apk add openjdk8
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/bin/javac
WORKDIR /ashish
ADD MultiplyNumbersOnContainers.java MultiplyNumbersOnContainers.java
ENTRYPOINT javac MultiplyNumbersOnContainers.java && java MultiplyNumbersOnContainers $NUMBER1 $NUMBER2
