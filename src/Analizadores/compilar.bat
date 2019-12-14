SET JFLEX_HOME= C:\Users\alexa\Downloads\jflex-1.7.0
cd C:\Users\alexa\Documents\NetBeansProjects\[Compi1VacasD2019]Proyecto1\src\Analizadores
java -jar %JFLEX_HOME%\lib\JFlex.jar Lexico.jflex Lexico.jflex
pause

SET CUP_HOME=C:\Users\alexa\Downloads
cd C:\Users\alexa\Documents\NetBeansProjects\[Compi1VacasD2019]Proyecto1\src\Analizadores
java -jar %CUP_HOME%\java-cup-11b.jar -parser parser Sintactico.cup
pause
