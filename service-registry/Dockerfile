# 初始镜像
FROM java:8
#FROM adoptopenjdk/openjdk8
# jar包名字需要更改为项目名字-版本号，后面app.jar 不变
# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/Asia/ShangHai /etc/localtime
#经测试，不加这一行有时会不生效。或系统重启后也会恢复成UTC时间
RUN echo "Asia/Shanghai" > /etc/timezone


ADD  target/*.jar app.jar
# 配置JVM启动参数
ENV JVM_ARGS=${JVM_ARGS}
EXPOSE 8761
# 优化jvm参数配置启动
ENTRYPOINT java ${JVM_ARGS} -Djava.security.egd=file:/dev/./urandom -jar app.jar
