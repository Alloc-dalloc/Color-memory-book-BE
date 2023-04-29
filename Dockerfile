FROM openjdk:11

WORKDIR /osogo

COPY color-memory-book-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]