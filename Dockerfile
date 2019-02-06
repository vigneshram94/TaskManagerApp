FROM java:8
EXPOSE 8087
ADD target/spring-docker-test.jar spring-docker-test.jar
ENTRYPOINT ["java","-jar","spring-docker-test.jar"]
