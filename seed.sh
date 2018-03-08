#!/bin/bash 
set -e
. settings

mvn exec:java -Dexec.mainClass="test.emnify.app.akkatest"  -Dexec.args="seed" -Dakka.remote.netty.tcp.hostname=$NODEIP
