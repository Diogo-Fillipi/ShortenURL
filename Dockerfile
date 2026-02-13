#BUILD

FROM maven:3.9.12-eclipse-temurin-25-alpine AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn -DskipTests clean package

#RUNTIME

FROM eclipse-temurin:25.0.2_10-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]