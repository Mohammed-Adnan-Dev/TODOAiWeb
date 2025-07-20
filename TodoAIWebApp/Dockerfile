# Use a base image with Tomcat and JDK
FROM tomcat:9.0-jdk17

# Remove default web apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR or app files
COPY target/TodoAIWebApp.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
