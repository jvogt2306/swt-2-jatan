FROM timbru31/java-node
RUN apt-get update && apt-get install unzip
RUN curl --insecure -o ./sonarscanner.zip -L https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-3.2.0.1227-linux.zip
RUN unzip sonarscanner.zip
RUN rm sonarscanner.zip
RUN mv sonar-scanner-3.2.0.1227-linux sonar-scanner

ENV SONAR_RUNNER_HOME=/root/sonar-scanner
ENV PATH $PATH:/root/sonar-scanner/bin

ARG JAR_FILE=build/libs/analysis-application-0.0.1-SNAPSHOT.jar
# cp spring-boot-web.jar /opt/app/app.jar
#WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 6015

ENTRYPOINT ["java","-jar","app.jar"]
