FROM openjdk:19
COPY target/ConnectFour-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
CMD java -jar ConnectFour-1.0-SNAPSHOT-jar-with-dependencies.jar