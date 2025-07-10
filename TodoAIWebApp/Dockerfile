FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM tomcat:9.0-jre11
COPY --from=build /app/target/ToDoAIWebApp.war /usr/local/tomcat/webapps/ROOT.war
ENV DATABASE_URL=${jdbc:mysql://sql12.freesqldatabase.com:3306/sql12345678L}
ENV DATABASE_USER=${sql12345678}
ENV DATABASE_PASSWORD=${BHV6lK1VWC}
EXPOSE 8080
CMD ["catalina.sh", "run"]
