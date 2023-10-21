#build
RUN ./gradlew build

FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE
ENV SPRING_PROFILES_ACTIVE local
ADD ${JAR_FILE} ecommerce.jar
ENTRYPOINT ["java", "-Dlogging.file=/api-ecommerce.log", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "ecommerce.jar"]
