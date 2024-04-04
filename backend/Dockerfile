FROM docker
COPY --from=docker/buildx-bin:latest /buildx /usr/libexec/docker/cli-plugins/docker-buildx

ENV SAVE_PATH /images
RUN echo "SAVE_PATH=$SAVE_PATH"

FROM openjdk:17-jdk
EXPOSE 443
ADD ./build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]