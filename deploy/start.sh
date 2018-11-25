#!/bin/sh
nohup java -Dspring.profiles.active=prod -jar sell.jar > /dev/null 2>&1 &
