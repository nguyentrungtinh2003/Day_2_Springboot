FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar Day_2_Springboot-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","Day_2_Springboot-0.0.1-SNAPSHOT.jar"]