FROM quay.io/zenlab/openjdk:11 AS BUILD_IMAGE
WORKDIR /src
COPY . .
RUN chmod +x gradlew
RUN ./gradlew assemble

FROM quay.io/sdase/openjdk-runtime:11-hotspot
ENV APP_OPTIONS -Djava.security.egd=file:/dev/./urandom
WORKDIR /opt/app
COPY --from=BUILD_IMAGE /src/build/libs/openshift-app-starter-*.jar app.jar
EXPOSE 8080
ENTRYPOINT java "$APP_OPTIONS" -jar app.jar