FROM openjdk:11

WORKDIR /osogo

VOLUME ["/var/log"]

COPY color-memory-book-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar"]