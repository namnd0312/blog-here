FROM openjdk:21-jdk
USER root
VOLUME /tmp
ARG JAR_FILE=target/blog-here-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
