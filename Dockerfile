FROM maven:3.6-jdk-8
ADD . /cxfbootsimple
WORKDIR /cxfbootsimple
RUN mvn clean
RUN mvn package
FROM adoptopenjdk/openjdk8:alpine-jre
COPY --from=0 /cxfbootsimple/target/calculator-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
CMD ["/resources"]