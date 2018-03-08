#!/bin/bash 
set -e
. settings
mvn exec:java -Dexec.mainClass="test.emnify.app.akkatest" -Dakka.remote.netty.tcp.port=0 -Dakka.cluster.seed-nodes="akka.tcp://testSystem@$SEEDIP:2551" -Dakka.remote.netty.tcp.hostname=$NODEIP
