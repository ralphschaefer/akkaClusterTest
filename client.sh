#!/bin/bash 
set -e

mvn exec:java -Dexec.mainClass="test.emnify.app.akkatest" -Dakka.remote.netty.tcp.port=0
