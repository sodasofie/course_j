FROM openjdk:11-jre-slim

WORKDIR /app

COPY ./target/course_java-0.0.1-SNAPSHOT.jar /app/course_java-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "course_java-0.0.1-SNAPSHOT.jar"]
