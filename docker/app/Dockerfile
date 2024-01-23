FROM maven:3.9.6-amazoncorretto-8 as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=maven target/accounting-system-*.jar ./accounting-system.jar
CMD ["java", "-jar", "./accounting-system.jar"]