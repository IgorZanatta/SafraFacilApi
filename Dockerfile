# Usar a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Criar um diretório para a aplicação
VOLUME /tmp

# Copiar o JAR gerado pelo Maven para o diretório do container
COPY target/*.jar app.jar

# Definir o comando de entrada
ENTRYPOINT ["java","-jar","/app.jar"]
