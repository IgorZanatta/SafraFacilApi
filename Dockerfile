# Fase de build
FROM ubuntu:latest AS build

# Atualizar o repositório e instalar OpenJDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o código fonte para o diretório de trabalho
COPY . .

# Imprimir variáveis de ambiente (para depuração)
RUN printenv

# Construir o projeto Maven
RUN mvn clean install -X

# Fase de produção
FROM openjdk:17-jdk-slim

# Expor a porta que a aplicação vai rodar
EXPOSE 8008

# Copiar o JAR da fase de build para a fase de produção
COPY --from=build /app/target/projeto-0.0.1-SNAPSHOT.jar app.jar

# Definir o comando de entrada
ENTRYPOINT ["java","-jar","/app.jar"]
