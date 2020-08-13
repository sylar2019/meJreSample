#!/bin/bash
# https://hub.docker.com/_/eclipse-mosquitto
sudo docker run -dti -p 1883:1883 -p 9001:9001 --name mosquitto eclipse-mosquitto
