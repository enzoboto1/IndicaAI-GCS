#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

cd "$SCRIPT_DIR/indicaai"
if [ ! -f mvnw ]; then
  echo "Erro: mvnw nao encontrado em indicaai/"
  exit 1
fi
chmod +x ./mvnw
./mvnw spring-boot:run &
BACKEND_PID=$!

cd "$SCRIPT_DIR/frontend"
if [ ! -f package.json ]; then
  echo "Erro: package.json nao encontrado em frontend/"
  kill "$BACKEND_PID"
  exit 1
fi
npm install
npm start &
FRONTEND_PID=$!

echo "Back-end rodando em http://localhost:8080"
echo "Front-end rodando em http://localhost:4200"
echo "Pressione CTRL+C para encerrar."
wait $BACKEND_PID $FRONTEND_PID
