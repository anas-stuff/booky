# Stage 1: Build the application
FROM openjdk:17-jdk-alpine as build

# Set the working directory
WORKDIR /app

# Copy the source code to the working directory
COPY . .

# Download dependencies build step
RUN ./gradlew build --no-daemon -x test

# Stage 3: Create the final image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
