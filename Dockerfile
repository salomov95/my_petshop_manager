FROM maven:3.8.3-openjdk-17 as base
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_NAME=${DATABASE_NAME}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
WORKDIR /build
COPY . /build
RUN mvn clean -Dspring.profiles.active="test" package

FROM openjdk:17-slim
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_NAME=${DATABASE_NAME}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
COPY --from=base /build/target/com.ssdev.mypet_*.jar .
CMD ["java", "-Dspring.profiles.active=production", "-jar", "com.ssdev.mypet_*.jar"]
