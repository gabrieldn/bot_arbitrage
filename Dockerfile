FROM openjdk:8u131-jdk-alpine
ENV JAVA_OPTS=""
WORKDIR /opt/teste
COPY /target/*.jar bin/teste.jar
CMD java $JAVA_OPTS -jar bin/teste.jar