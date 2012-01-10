#!/bin/bash
./setVars.bat
# change directory 
cd $HW_HOME/bin;   
rmic controllers.HealthWatcherFacade
pause
