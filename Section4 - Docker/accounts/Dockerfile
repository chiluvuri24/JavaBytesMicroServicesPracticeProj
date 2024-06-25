#start with base image containing Java Run Time
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER eazyBytes.com

#Add the application jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the appliction
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]