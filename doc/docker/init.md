docker

curl -sSL https://get.docker.com/ | sh

------------------
docker-compose

curl -L https://get.daocloud.io/docker/compose/releases/download/1.26.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
------------------
nacos
 
docker run --env MODE=standalone --name nacos -d -p 8848:8848 nacos/nacos-server

------------------

jenkins 

FROM jenkins/jenkins:lts;

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


/var/jenkins_home/secrets/initialAdminPassword

apt-get update
apt-get install vim 

docker run -d -p 8080:8080 -p 50001:50000 --name jenkins -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker docker-jenkins

sed -i 's#http://updates.jenkins-ci.org/download#https://mirrors.tuna.tsinghua.edu.cn/jenkins#g' default.json && sed -i 's#http://www.google.com#https://www.baidu.com#g' default.json


---------

Harbor
下载 harbor
wget https://github.com/goharbor/harbor/releases/download/v2.0.1/harbor-online-installer-v2.0.1.tgz
解压
tar xvf harbor-online-installer-v2.0.1.tgz
自行创建ssl证书
然后
./prepare
再docker-compose up -d
