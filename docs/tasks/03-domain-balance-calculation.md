# Task 03 - Balance Calculation

## Objetivo
Implementar cálculo de saldo dentro de LoyaltyAccount.

## Regras

Saldo = soma de:
- EARN válidos
- REDEEM
- EXPIRE
- ADJUSTMENT

Pontos expirados não contam.

## Comportamentos Esperados

- getBalance()
- getPointsExpiringBefore(date)

## Tarefas

- Adicionar lista de transações no LoyaltyAccount
- Implementar cálculo de saldo
- Ignorar pontos expirados

## Testes Unitários

- Saldo com múltiplos EARN
- Saldo após REDEEM
- Saldo com pontos expirados
- Consumo parcial de múltiplos EARN

## Critério de Aceite

- Saldo nunca negativo
- Todos cenários de teste passam    