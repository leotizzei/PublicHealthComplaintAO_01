INSTRUCTIONS FOR PUBLICHEALTHCOMPLAINT SPL:

1. Execute "ant publichealthcomplaint-aspectj"

2. Execute registry.sh

3. Execute serverBD.sh

4. Execute servlet.sh  - start java EE server

6. Open the URL http://localhost:8080/servlet/healthwatcher.view.servlets.ServletWebServer?file=index.html

Additional points that require acting upon before compiling HealthWatcher.

1. Edit \bats\setVars.bat and modify the HW_HOME path to point to the directory which contains the \bin directory.

2. Change the constant FORM_PATH in the \src\healthwatcher\Constants.java before you compile healthwatcher to point to where healthwatcher's html pages are located on your system.

3. When adding the database as a data source in windows make sure you give it the name 'test'.
