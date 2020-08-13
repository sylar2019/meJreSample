#!/bin/bash
#https://hub.docker.com/r/hivemq/hivemq-ce
sudo docker run -dti -p 1883:1883 --name hivemq-ce hivemq/hivemq-ce
