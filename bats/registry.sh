#!/bin/bash
echo "Called setVars.sh"
rmiregistry -J-Djava.security.policy=rmi.policy &
echo "Called rmiregistry"
