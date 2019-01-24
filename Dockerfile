FROM java:8
ADD target/spring-docker-test.jar spring-docker-test.jar
RUN bash -c 'touch /spring-docker-test.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/spring-docker-test.jar"]