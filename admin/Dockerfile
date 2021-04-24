# 初始镜像
FROM java:8
#FROM adoptopenjdk/openjdk8
# jar包名字需要更改为项目名字-版本号，后面app.jar 不变
ADD  target/admin-0.0.1-SNAPSHOT.jar app.jar
# 配置JVM启动参数
ENV JVM_ARGS=${JVM_ARGS}
EXPOSE 8080
# 优化jvm参数配置启动
ENTRYPOINT java ${JVM_ARGS} -Djava.security.egd=file:/dev/./urandom -jar app.jar
