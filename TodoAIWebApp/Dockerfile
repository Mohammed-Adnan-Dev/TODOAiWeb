# -------- STAGE 1: Build using Maven --------
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy the source code to the container
COPY . .

# Build the WAR file
RUN mvn clean package -DskipTests

# -------- STAGE 2: Run on Tomcat --------
FROM tomcat:9.0

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file from Maven build
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
