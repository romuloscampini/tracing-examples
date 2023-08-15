FROM maven:3.8-amazoncorretto-11 as dependencies
WORKDIR /opt/app
COPY . .
RUN mvn dependency:resolve


FROM dependencies as build
RUN mvn clean package


FROM amazoncorretto:11-alpine-jdk
ENV JAVA_OPTS=""
ENV JAVA_EXTRA_OPTS=""
EXPOSE 9090
COPY --from=build /opt/app/target/animal-name-service-*.jar animal-name-service.jar
CMD java $JAVA_OPTS $JAVA_EXTRA_OPTS -jar /animal-name-service.jar
