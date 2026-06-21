@echo off
cd /d "%~dp0\frontend"
if not exist package.json (
  echo Erro: package.json nao encontrado em frontend\
  pause
  exit /b 1
)
npm install
if errorlevel 1 (
  echo Erro: npm install falhou
  pause
  exit /b 1
)
call npm start
pause
