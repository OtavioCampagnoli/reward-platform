# Task 00 - Project Setup

## Objetivo
Criar estrutura base do projeto seguindo Clean Architecture.

## Estrutura de Pacotes

com.yourcompany.loyalty
  domain
  application
  infrastructure
  api

## Requisitos Técnicos

- Java 17
- Spring Boot 3+
- Maven ou Gradle
- Banco: PostgreSQL (ou H2 para dev)
- Flyway ou Liquibase
- JUnit 5

## Tarefas

- Criar projeto Spring Boot
- Configurar dependências:
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - validation
  - test
- Configurar banco
- Configurar migration inicial
- Criar endpoint GET /health

## Critério de Aceite

- Aplicação sobe sem erro
- GET /health retorna 200
- Teste simples de contexto carrega com sucesso