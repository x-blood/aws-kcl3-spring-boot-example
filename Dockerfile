FROM amazoncorretto:21

EXPOSE 8080

ENV LANG C.UTF-8

RUN yum -y update && yum -y install \
curl \
bash \
cpio \
expat \
glibc \
python2 \
libxml \
openldap \
libssh2 \
libxml2 \
shadow-utils \
e2fsprogs

RUN useradd -u 9001 -ms /bin/bash dockeruser
USER dockeruser
WORKDIR /home/dockeruser

COPY target/aws-kcl3-spring-boot-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java","-jar","app.jar"]