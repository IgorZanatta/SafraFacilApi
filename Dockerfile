# Use an OpenJDK base image with JDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Install Maven and build the project
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean install -DskipTests && \
    rm -rf /var/lib/apt/lists/*

# Set the default command to run your app
CMD ["java", "-jar", "target/projeto-0.0.1-SNAPSHOT.jar"]
