#!/bin/bash
clear;

# define JAVA_HOME
export JAVA_HOME=/home/leonardo/programs/jdk1.6.0_14
echo "JAVA_HOME is $JAVA_HOME"

# define PHC_HOME 
export PHC_HOME=/home/leonardo/workspace3/PublicHealthComplaintAO_01
echo "PHC_HOME is $PHC_HOME"

#define path
export path=$JAVA_HOME/bin:$path
export path=$PHC_HOME/bats:$path
echo $path
export classpath=$JAVA_HOME/lib/tools.jar
export classpath=$classpath:$JAVA_HOME/lib/rt.jar
export classpath=$classpath:$PHC_HOME/lib/classes12.zip
export classpath=$classpath:$PHC_HOME/lib/jsdk.jar
export classpath=$classpath:$PHC_HOME/tmp/classes
echo $classpath

