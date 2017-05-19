#!/bin/bash
sudo chown -R ubuntu:ubuntu /home/ubuntu/XimpApi
cd /home/ubuntu/XimpApi
mvn clean install
sudo mv target/XimpApi.war /var/lib/tomcat7/webapps/XimpApi.war