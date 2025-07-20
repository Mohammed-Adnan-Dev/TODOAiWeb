# -------- STAGE 1: Build using Maven --------
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside container to the submodule
WORKDIR /app

# Copy entire source code inside container
COPY . .

# Build WAR from /app (where pom.xml exists)
RUN mvn clean package -DskipTests

# -------- STAGE 2: Deploy with Tomcat --------
FROM tomcat:9.0

# Remove default web apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR into Tomcat's webapps directory
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
