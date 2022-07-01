FROM openjdk:11-jre-slim-buster
COPY ./build/libs/eureka-1.0-SNAPSHOT.jar .
EXPOSE 8100
CMD ["java", "-jar", "eureka-1.0-SNAPSHOT.jar"]