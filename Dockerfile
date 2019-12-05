FROM openjdk:11 as builder

COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew bootJar

FROM openjdk:11-jre-slim
COPY --from=builder /home/gradle/src/build/libs/address-book-0.0.1.jar /app/
WORKDIR /app

ADD scripts/start.sh /app/start.sh

CMD ["/app/start.sh"]