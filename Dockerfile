FROM openjdk:8
ADD ./target/demo.jar /usr/local/
CMD ["java", "-jar", "/usr/local/demo.jar"]
EXPOSE 8080