# Dockerfile
ARG BASE_IMG_REPO_URL=eclipse-temurin
ARG BASE_IMG_REPO_TAG=21-jre-alpine

FROM $BASE_IMG_REPO_URL:$BASE_IMG_REPO_TAG
LABEL name=api-sample

RUN apk add --no-cache bash
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN mkdir -p /opt/api-sample && chown javauser:javauser /opt/api-sample

WORKDIR /opt/api-sample
USER javauser

COPY target/*.jar target/dependency/*.jar /opt/api-sample/

ENV JAVA_TOOL_OPTIONS "-javaagent:aws-opentelemetry-agent.jar -Dotel.javaagent.extensions=otel-extension-filter-path.jar"
ENV OTEL_TRACES_SAMPLER "filter-sampler"

CMD ["java", "-jar", "api.jar"]