@echo off
cd /d "%~dp0"
java -jar checkstyle-10.12.3-all.jar -c checkstyle.xml controlador\PlatilloControlador.java
pause
