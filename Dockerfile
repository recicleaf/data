FROM openjdk:11-slim

COPY ./target/data-service.jar /data-service.jar

CMD ["java", "-jar", "/data-service.jar"]