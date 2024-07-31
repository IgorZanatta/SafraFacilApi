# Use an OpenJDK base image with JDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Build the project
RUN mvn clean install -X

# Set the default command to run your app
CMD ["java", "-jar", "target/your-app.jar"]
