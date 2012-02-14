#!/bin/bash
./setVars.sh
echo "Called setVars.sh"
java healthwatcher.business.HealthWatcherFacade
echo "Executing healthwatcher.business.HealthWatcherFacade"
pause

