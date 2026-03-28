# Task 05 - RedeemRewardUseCase

## Objetivo
Permitir resgate de recompensa usando pontos.

## Entrada

- customerId
- rewardId

## Regras

- Conta deve estar ACTIVE
- Saldo suficiente
- Consumir pontos por ordem de expiração (FIFO)
- Gerar transação REDEEM

## Tarefas

- Criar entidade Reward
- Criar RewardRepository
- Implementar RedeemRewardUseCase
- Implementar lógica de consumo por validade

## Testes

- Resgate com saldo suficiente
- Resgate com saldo insuficiente
- Consumo parcial de múltiplos EARN

## Critério de Aceite

- Lógica de consumo está no domínio
- UseCase apenas coordena