# Task 04 - EarnPointsUseCase

## Objetivo
Permitir acúmulo de pontos após compra.

## Entrada

- customerId
- orderId
- amountSpent

## Regras

- Conta deve estar ACTIVE
- amountSpent > 0
- 1 real = 1 ponto
- Pontos expiram em 12 meses

## Saída

- pontos gerados

## Tarefas

- Criar interface LoyaltyAccountRepository
- Criar EarnPointsUseCase
- Adicionar transação EARN à conta
- Persistir conta

## Testes

- Conta ativa acumula pontos
- Conta bloqueada falha
- amountSpent inválido falha

## Critério de Aceite

- UseCase testado sem Spring
- Repositório mockado