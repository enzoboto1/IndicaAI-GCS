@echo off
cd /d "%~dp0\indicaai"
if not exist mvnw.cmd (
  echo Erro: mvnw.cmd nao encontrado em indicaai\
  exit /b 1
)
call mvnw.cmd spring-boot:run
