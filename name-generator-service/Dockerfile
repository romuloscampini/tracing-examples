FROM amazoncorretto:11-alpine-jdk
EXPOSE 8080
ENV JAVA_OPTS="-javaagent:/opt/app/opentelemetry-javaagent.jar"
COPY target/name-generator-service-*.jar name-generator-service.jar
CMD java $JAVA_OPTS $JAVA_EXTRA_OPTS -jar /name-generator-service.jar
