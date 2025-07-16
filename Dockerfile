FROM maven:3.8.5-openjdk-17 as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build ./build/target/*.jar ./register-manager-authorization-server.jar

expose 9090
expose 9091

ENV SERVER_ISSUER='http://register-manager-authorization-server:9090'
ENV DATASOURCE_URL='jdbc:postgresql://postgresdb:5432/register_manager'
ENV DATASOURCE_USERNAME='postgres'
ENV DATASOURCE_PASSWORD='postgres'
ENV SHOW_SQL='true'
ENV DDL_AUTO='update'
ENV FORMAT_SQL='true'
ENV APP_PORT='9090'
ENV MONITORING_PORT='9091'
ENV MONITORING_LIST='*'
ENV LOG_NAME='register-manager-authorization-server.log'
ENV LOG_LEVEL='warn'
ENV ACCESS_TOKEN_EXPIRATION='30'
ENV REFRESH_TOKEN_EXPIRATION='60'

ENTRYPOINT java -jar register-manager-authorization-server.jar