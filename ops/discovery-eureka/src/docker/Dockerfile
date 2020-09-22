FROM openjdk:8-jdk-alpine
MAINTAINER sylar <81623859@qq.com>
ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
RUN mkdir /app
ARG ACTIVE_ENV=default
ENV APP_PATH /app/app.jar
ADD app.jar ${APP_PATH}
ENV JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${ACTIVE_ENV} -Duser.timezone=GMT+08 -Xms256m -Xmx512m
RUN /bin/sh -c 'touch ${APP_PATH}'
EXPOSE 8761
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_PATH}