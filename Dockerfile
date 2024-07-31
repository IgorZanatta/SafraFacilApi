# Usar a imagem base do OpenJDK 17
FROM ubunto:latest AS build

RUN apt-get update
RUN apt-get install openjdk:17-jdk -y

COPY . .

RUN apt-get install maven -y

RUN mvn clean install


FROM openjdk:17-jdk-slim

EXPOSE 8008

COPY --from=build /target/projeto-0.0.1-SNAPSHOT.jar app.jar

# Definir o comando de entrada
ENTRYPOINT ["java","-jar","/app.jar"]

