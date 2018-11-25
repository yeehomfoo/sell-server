#!/bin/sh
mvn clean package -Dmaven.test.skip=true
scp target/sell.jar root@127.0.0.1:/opt/java/apps