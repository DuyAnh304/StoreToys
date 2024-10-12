FROM openjdk:17-slim
ARG STORETOYS_JAR=target/StoreToys-API-0.0.1-SNAPSHOT.jar
WORKDIR /app
ADD ${STORETOYS_JAR} ./storetoys.jar
COPY ./uploads ./uploads
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "storetoys.jar"]