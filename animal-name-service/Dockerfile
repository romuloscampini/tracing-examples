FROM amazoncorretto:11-alpine-jdk
EXPOSE 9000
ENV JAVA_OPTS="-javaagent:/opt/app/opentelemetry-javaagent.jar"
COPY target/animal-name-service-*.jar animal-name-service.jar
CMD java $JAVA_OPTS $JAVA_EXTRA_OPTS -jar /animal-name-service.jar
