# Task 01 - LoyaltyAccount Domain

## Objetivo
Criar entidade LoyaltyAccount com regras básicas.

## Regras

- Deve possuir id
- Deve possuir status (ACTIVE, BLOCKED)
- Conta inicia ACTIVE
- Conta BLOCKED não permite operações

## Métodos Esperados

- block()
- activate()
- ensureActive()

## Tarefas

- Criar enum LoyaltyAccountStatus
- Criar classe LoyaltyAccount
- Criar exception de domínio

## Testes Unitários

- Conta inicia ACTIVE
- Conta pode ser bloqueada
- Conta bloqueada lança exception ao operar

## Critério de Aceite

- Todos testes unitários passam
- Nenhuma dependência de Spring no domínio  