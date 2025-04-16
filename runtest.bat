@echo off
echo Running TestNG tests...
cd /d %~dp0

REM Clean and compile the project (optional but good)
mvn clean compile

REM Run TestNG suite using Maven Surefire plugin
mvn test -Dsurefire.suiteXmlFiles=testng.xml

pause
