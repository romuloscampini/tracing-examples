FROM amazoncorretto:11-alpine-jdk
EXPOSE 8090
ENV JAVA_OPTS="-javaagent:/opt/app/opentelemetry-javaagent.jar"
COPY target/scientist-name-service-*.jar scientist-name-service.jar
CMD java $JAVA_OPTS $JAVA_EXTRA_OPTS -jar /scientist-name-service.jar
