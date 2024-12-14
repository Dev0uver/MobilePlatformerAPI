FROM maven:3.8.4-openjdk-17-slim AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=build /app/target/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
