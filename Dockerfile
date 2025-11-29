FROM sbtscala/scala-sbt:graalvm-community-21.0.2_1.11.7_3.7.4 AS builder
WORKDIR /app

COPY . .

RUN sbt fullOptJS

FROM python:3.11-slim
WORKDIR /app

COPY --from=builder /app/target/public .

EXPOSE 8080
CMD ["python3", "-m", "http.server", "8080"]
