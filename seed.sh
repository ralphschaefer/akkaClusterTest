#!/bin/bash 
set -e

mvn exec:java -Dexec.mainClass="test.emnify.app.akkatest"  -Dexec.args="seed"
