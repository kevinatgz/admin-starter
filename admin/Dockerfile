# 初始镜像
FROM java:8
#FROM openjdk:latest
#FROM adoptopenjdk/openjdk8
# jar包名字需要更改为项目名字-版本号，后面app.jar 不变

# 设置时区
ENV TZ=Asia/Shanghai
RUN  ln -sf /usr/share/zoneinfo/Asia/ShangHai /etc/localtime && apt-get install tzdata && apt-get clean && apt-get autoclean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
#经测试，不加这一行有时会不生效。或系统重启后也会恢复成UTC时间
RUN echo "Asia/Shanghai" > /etc/timezone
#RUN dpkg-reconfigure -f noninteractive tzdata

RUN mkdir -p /Users/kevin/Java/Project/admin-starter/logs/admin/info
RUN mkdir -p /Users/kevin/Java/Project/admin-starter/logs/admin/warn
RUN mkdir -p /Users/kevin/Java/Project/admin-starter/logs/admin/error
RUN mkdir -p /Users/kevin/Java/Project/admin-starter/logs/admin/json
ADD  target/admin-0.0.1-SNAPSHOT.jar app.jar
# 配置JVM启动参数
ENV JVM_ARGS=${JVM_ARGS}
EXPOSE 8008
# 优化jvm参数配置启动
ENTRYPOINT java ${JVM_ARGS} -Djava.security.egd=file:/dev/./urandom -jar app.jar
