FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /opt/lewebseite/app.jar
COPY application.properties /opt/lewebseite/application.properties 
ENTRYPOINT ["java", "-jar", "/opt/lewebseite/app.jar", "-Dspring.config.location=/opt/lewebseite/application.properties"]
