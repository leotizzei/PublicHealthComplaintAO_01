#!/bin/bash

#define HealthWatcher home dir
export HW_HOME=..
export CLASSPATH=$HW_HOME/tmp/healthwatcher-basic.jar:$HW_HOME/lib/mysql-connector-java-5.1.14-bin.jar:$HW_HOME/lib/aspectjrt.jar
echo "Running rmiregistry process..."
rmiregistry &
ret_pid=$!
echo "Running healthwatcher.business.HealthWatcherFacade RMI..."
java healthwatcher.business.HealthWatcherFacade
echo "\nKilling rmiregistry process..."
kill ${ret_pid}

