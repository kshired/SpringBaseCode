FROM openjdk:11
COPY build/libs/*.jar app.jar

ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]