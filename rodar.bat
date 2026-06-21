@echo off
echo Iniciando SistemaFinanceiro...
cd /d "c:\Users\vitin\Desktop\trbalho poo\SistemaFinanceiro"
"C:\Program Files\NetBeans-22\netbeans\java\maven\bin\mvn.cmd" exec:java -Dexec.mainClass=com.mycompany.sistemafinanceiro.SistemaFinanceiro
echo.
echo === PROGRAMA ENCERRADO ===
pause
