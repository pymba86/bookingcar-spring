FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    SLEEP_START=0 \
    JAVA_OPTS=""

CMD echo "The application will start in ${SLEEP_START}s..." && \
    sleep ${SLEEP_START} && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar

EXPOSE 8080

ADD *.jar /app.jar