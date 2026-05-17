FROM sbtscala/scala-sbt:graalvm-community-21.0.2_1.11.7_3.7.4 AS builder
WORKDIR /app
COPY . .
RUN sbt fullOptJS

FROM nginx:alpine
COPY --from=builder /app/target/public /usr/share/nginx/html
RUN mv /usr/share/nginx/html/index.html /usr/share/nginx/html/index.template.html

EXPOSE 80
CMD ["/bin/sh", "-c", "envsubst '$$LANG_POLLING_URL' < /usr/share/nginx/html/index.template.html > /usr/share/nginx/html/index.html && nginx -g 'daemon off;'"]
