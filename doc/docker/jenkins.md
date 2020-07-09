FROM jenkins/jenkins:lts

USER root
#清除了基础镜像设置的源，切换成阿里云的jessie源
RUN echo '' > /etc/apt/sources.list.d/jessie-backports.list \
  && echo "deb http://mirrors.aliyun.com/debian jessie main contrib non-free" > /etc/apt/sources.list \
  && echo "deb http://mirrors.aliyun.com/debian jessie-updates main contrib non-free" >> /etc/apt/sources.list \
  && echo "deb http://mirrors.aliyun.com/debian-security jessie/updates main contrib non-free" >> /etc/apt/sources.list
#更新源并安装缺少的包
RUN apt-get update && apt-get install -y libltdl7

ARG dockerGid=999

RUN echo "docker:x:${dockerGid}:jenkins" >> /etc/group \
USER jenkins


docker run -d -p 8080:8080 -p 50001:50000 --name jenkins -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker jenkins

sed -i 's#http://updates.jenkins-ci.org/download#https://mirrors.tuna.tsinghua.edu.cn/jenkins#g' default.json && sed -i 's#http://www.google.com#https://www.baidu.com#g' default.json
