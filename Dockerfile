#build stage
FROM maven:3.9.8-eclipse-temurin-22 AS build
EXPOSE 8080
#create directory on container
WORKDIR /app

#copy pom.xml to "." (container root)
COPY pom.xml .

#copy source code
COPY src ./src

#compile project
RUN mvn clean install

#production stage
FROM openjdk:22
WORKDIR /app

#copy artefact compiling the compiler stage
COPY --from=build /app/target/coffee-api-0.0.1-SNAPSHOT.jar ./coffee-api.jar

#start application
CMD ["java", "-jar", "./coffee-api.jar"]