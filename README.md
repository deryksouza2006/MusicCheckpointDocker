@'
# Checkpoint 2 - DevOps Tools & Cloud Computing

## Pre-requisitos

Antes de executar o projeto, a maquina precisa ter:

- Git instalado
- Docker instalado e em execucao
- PowerShell disponivel
- Acesso a internet na primeira execucao, pois o Docker/Maven ira baixar as imagens e dependencias

Este projeto foi testado em ambiente Windows com Docker Desktop e PowerShell.

Nao e obrigatorio utilizar uma VM Ubuntu. A VM e apenas uma alternativa para executar Docker em nuvem ou em ambiente Linux. O requisito principal e que o Docker esteja instalado e funcionando.

Para verificar se o Docker esta funcionando, execute:

```powershell
docker ps
```

Se aparecer uma tabela, mesmo vazia, o Docker esta pronto para uso.

Caso apareca erro de conexao com o Docker, abra o Docker Desktop e aguarde ele iniciar completamente.

---

## Como executar rapidamente

Com o Docker aberto e funcionando, abra o PowerShell na raiz do repositorio clonado e rode os scripts abaixo na ordem.

### 1. Criar rede Docker e volume do banco

```powershell
.\scripts\01-criar-rede-volume.ps1
```

### 2. Subir o container do banco PostgreSQL

```powershell
.\scripts\02-subir-banco.ps1
```

### 3. Subir o container da API Java

```powershell
.\scripts\03-subir-api.ps1
```

A primeira execucao pode demorar alguns minutos, pois o container Maven baixa as dependencias e compila o projeto.

Para acompanhar os logs da API:

```powershell
docker logs -f cp2_rm563412_api
```

Quando aparecer `Started MusicApplication`, a API esta pronta.

### 4. Testar o CRUD completo

```powershell
.\scripts\04-testar-crud.ps1
```

Esse script executa:

- GET inicial na API
- INSERT de um genero
- SELECT no banco mostrando o registro criado
- UPDATE do registro
- SELECT no banco mostrando o registro alterado
- DELETE do registro
- SELECT no banco mostrando que o registro foi removido

### 5. Mostrar as evidencias Docker exigidas

```powershell
.\scripts\05-evidencias-docker.ps1
```

Esse script exibe os comandos exigidos no checkpoint:

```powershell
docker ps
docker image ls
docker volume ls
docker network ls
```

### 6. Limpar o ambiente, se necessario

Atencao: este comando remove os containers, a rede e o volume do banco.

```powershell
.\scripts\06-limpar-ambiente.ps1
```

### Caso o PowerShell bloqueie os scripts

Execute:

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
```

Depois rode os scripts novamente.

---

## Projeto

Este projeto foi desenvolvido para o 2o Checkpoint de DevOps Tools & Cloud Computing.

O objetivo e demonstrar a migracao de um ambiente de desenvolvimento para Docker, utilizando dois containers que interagem entre si por meio de uma rede Docker:

- Container da API Java Spring Boot
- Container do banco de dados PostgreSQL

A API realiza operacoes de CRUD na entidade `Genre`, e as alteracoes sao comprovadas diretamente no banco PostgreSQL.

---

## Integrantes

| Nome | RM |
|---|---|
| Deryk de Souza Queiroz | 563412 |
| Lucas Goncalves Viana | 563254 |
| Vinicius Paschoeto da Silva | 563089 |

---

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Maven
- PostgreSQL 16
- Docker
- Docker Desktop
- PowerShell

---

## Estrutura Docker

### Rede Docker

```text
cp2_rm563412_network
```

### Volume Docker

```text
cp2_rm563412_pgdata
```

### Containers

```text
cp2_rm563412_api
cp2_rm563412_db
```

### Imagens utilizadas

```text
maven:3.9-eclipse-temurin-17
postgres:16
```

---

## Requisitos atendidos

| Requisito | Implementacao |
|---|---|
| Pelo menos dois containers | API Java e PostgreSQL |
| Um container deve ser banco de dados | PostgreSQL |
| Banco com volume nomeado | cp2_rm563412_pgdata |
| Containers em uma rede Docker | cp2_rm563412_network |
| Containers com portas expostas | API 8080:8080 e banco 5432:5432 |
| Containers com variaveis de ambiente | POSTGRES_DB, POSTGRES_USER, POSTGRES_PASSWORD e variaveis da API |
| Containers em segundo plano | Uso do parametro `-d` |
| Nome dos containers com RM | cp2_rm563412_api e cp2_rm563412_db |
| CRUD completo | INSERT, SELECT, UPDATE e DELETE em `genre` |

---

## Scripts disponiveis

| Script | Funcao |
|---|---|
| `01-criar-rede-volume.ps1` | Cria a rede Docker e o volume nomeado |
| `02-subir-banco.ps1` | Sobe o container PostgreSQL |
| `03-subir-api.ps1` | Sobe o container da API Java |
| `04-testar-crud.ps1` | Executa e comprova o CRUD |
| `05-evidencias-docker.ps1` | Mostra os comandos exigidos no checkpoint |
| `06-limpar-ambiente.ps1` | Remove containers, rede e volume |

---

## Configuracoes do banco

```text
Database: cp2db
User: cp2user
Password: cp2pass
Porta: 5432
```

A API se conecta ao banco pela rede Docker usando o nome do container:

```text
jdbc:postgresql://cp2_rm563412_db:5432/cp2db
```

---

## Endpoints principais

### Listar generos

```powershell
Invoke-RestMethod -Method Get -Uri "http://localhost:8080/genres"
```

### Listar musicas

```powershell
Invoke-RestMethod -Method Get -Uri "http://localhost:8080/musics"
```

---

## Acessar o banco pelo terminal

```powershell
docker exec -it cp2_rm563412_db psql -U cp2user -d cp2db
```

Listar tabelas:

```sql
\dt
```

Consultar generos:

```sql
select * from genre;
```

Consultar musicas:

```sql
select * from music;
```

Sair do PostgreSQL:

```sql
\q
```

---

## Comandos obrigatorios para evidencia

```powershell
docker ps
docker image ls
docker volume ls
docker network ls
```

Esses comandos tambem podem ser executados pelo script:

```powershell
.\scripts\05-evidencias-docker.ps1
```

---

## Repositorio

https://github.com/deryksouza2006/MusicCheckpointDocker

## Video no YouTube

https://youtu.be/L9IA4hRMPwI?si=g9tW7pfBbUjIGcHl

'@ | Set-Content -Path .\README.md -Encoding UTF8