FROM openjdk:8-jdk

WORKDIR application

COPY target/university-students-0.0.1-SNAPSHOT.jar /application

EXPOSE 8888

CMD java -jar university-students-0.0.1-SNAPSHOT.jar