# Usar uma imagem base do Maven para construir o projeto
FROM maven:3.8.4-openjdk-11 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o código fonte do projeto para o contêiner
COPY . .

# Construir o projeto Maven
RUN mvn clean install -X

# Usar uma imagem base do OpenJDK para executar a aplicação
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR da fase de build para a fase de produção
COPY --from=build /app/target/projeto-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]
