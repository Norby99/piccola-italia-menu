FROM sbtscala/scala-sbt:graalvm-community-21.0.2_1.11.7_3.7.4
WORKDIR /app

COPY . .

RUN sbt fullOptJS
