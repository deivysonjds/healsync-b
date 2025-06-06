FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY . .
RUN cd healsync && ./gradlew build
CMD ["java", "-jar", "healsync/build/libs/healsync-0.0.1-SNAPSHOT.jar"]