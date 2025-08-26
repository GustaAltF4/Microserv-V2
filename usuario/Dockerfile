FROM amazoncorretto:17-alpine-jdk


WORKDIR /app


COPY target/usuario-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8083


ENTRYPOINT ["java", "-jar", "app.jar"]