FROM openjdk:17
ADD target/estacion-meteorologica.jar estacion-meteorologica.jar

FROM amazoncorretto:17-alpine3.16

RUN apk update && apk add --no-cache maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src
RUN mvn -DskipTest=True: package

CMD ["java", "-jar","estacion-meteorologica.jar"]
