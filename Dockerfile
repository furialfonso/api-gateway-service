FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/api-gateway-0.0.1-SNAPSHOT.jar cowapigateway.jar
ENTRYPOINT exec java $JAVA_OPTS -jar cowapigateway.jar
# alwais use mvn clean install -DskipTests for set the profile only test or prod