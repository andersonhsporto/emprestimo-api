FROM maven:3.8.6-eclipse-temurin-19-alpine AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests


FROM eclipse-temurin:19-jdk-alpine
RUN mkdir /app
COPY --from=build /project/target/emprestimo-api-0.0.1-SNAPSHOT.jar /app/emprestimo-api-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD "java" "-jar" "emprestimo-api-0.0.1-SNAPSHOT.jar"
