# IndicaAI

IndicaAI é uma aplicação simples de apresentação que conecta alunos a vagas de tecnologia com base em compatibilidade de habilidades.

## Visão geral

- Back-end: Spring Boot + H2 em memória
- Front-end: Angular
- Banco de dados: H2 em memória para execução local simples
- Recomendação: compara habilidades do aluno com requisitos da vaga e calcula percentual de compatibilidade

## Estrutura do projeto

- `indicaai/` — back-end Spring Boot
- `frontend/` — front-end Angular

## Como executar

### Requisitos

- Java 11 instalado
- Node.js e npm instalados
- No Linux/macOS ou no Windows com Git Bash / WSL, use o terminal shell
- No Windows nativo, use PowerShell ou Prompt de Comando para os scripts `.bat`

### 1. Executar o back-end

No Windows:

```bat
cd indicaai
mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
cd indicaai
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

### 2. Executar o front-end

No Windows:

```bat
cd frontend
npm install
npm start
```

No Linux/macOS:

```bash
cd frontend
npm install
npm start
```

O front-end ficará disponível em `http://localhost:4200`.

### 3. Executar ambos juntos com um único comando

No Linux/macOS ou no Windows com Git Bash / WSL:

```bash
chmod +x run.sh
./run.sh
```

Se você estiver no Windows nativo e não tiver Bash, use os dois scripts separados em duas janelas:

```bat
start-backend.bat
```

```bat
start-frontend.bat
```

> Nota: este projeto entrega scripts de execução simples. Não há um instalador `.exe` ou `.app` gerado automaticamente aqui. O comando `./run.sh` é a forma mais fácil de iniciar o projeto quando você tem um shell Bash disponível.

## Endpoints principais do back-end

- `GET /api/alunos`
- `POST /api/alunos`
- `PUT /api/alunos/{id}`
- `DELETE /api/alunos/{id}`
- `GET /api/vagas`
- `POST /api/vagas`
- `PUT /api/vagas/{id}`
- `DELETE /api/vagas/{id}`
- `GET /api/recomendacoes/aluno/{id}`
- `GET /api/recomendacoes/vaga/{id}`

## Documentação e código

O código-fonte está disponível neste repositório. Para publicar no GitHub:

1. Crie um repositório no GitHub
2. Adicione o remote:
   ```bash
git remote add origin <URL_DO_REPOSITORIO>
```
3. Faça push:
   ```bash
git push -u origin main
```
```

## Observações

- O back-end usa H2 em memória para facilitar testes e apresentação.
- Os dados são armazenados apenas durante o tempo de execução do aplicativo: quando o servidor for desligado, os registros são perdidos.
- Por isso, é necessário cadastrar alunos e vagas sempre que reiniciar a aplicação para preencher o sistema e ver as recomendações.
- Se uma ação não for executada, tente clicar novamente ou clique fora do formulário para forçar a atualização da tela.
- O front-end depende de Node.js/NPM e Angular CLI.
- A aplicação foca na simplicidade e funcionalidade para sua exibição em execução.
- Quaisquer dúvidas ou pontos a esclarecer, contatar o e-mail: enzoboto@alu.ufc.br.
