FROM jenkins/jenkins:latest

USER root
#Install docker
RUN curl https://get.docker.com/ > dockerinstall && chmod 777 dockerinstall && ./dockerinstall

#Install docker compose
RUN curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
RUN chmod +x /usr/local/bin/docker-compose
#RUN groupadd docker
RUN usermod -aG docker jenkins
RUN newgrp docker

#Cleanup
RUN rm -r /dockerinstall

#USER jenkins