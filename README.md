# Checkpoint 2 - DevOps Tools & Cloud Computing

## Projeto

Este projeto foi desenvolvido para o 2o Checkpoint de DevOps Tools & Cloud Computing.

O objetivo e demonstrar a migracao de um ambiente de desenvolvimento para Docker, utilizando dois containers que interagem entre si por meio de uma rede Docker:

- Container da API Java Spring Boot
- Container do banco de dados PostgreSQL

A API realiza operacoes de CRUD na entidade `Genre`, e as alteracoes sao comprovadas diretamente no banco PostgreSQL.

## Integrantes

| Nome | RM |
|---|---|
| Deryk de Souza Queiroz | 563412 |
| Lucas Golcaves Viana | 563254 |
| Vinicius Paschoeto da Silva | 563089 |

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Maven
- PostgreSQL 16
- Docker
- Docker Desktop
- PowerShell

## Estrutura Docker

### Rede Docker

```text
cp2_rm563412_network