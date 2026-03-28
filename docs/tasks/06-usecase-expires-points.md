# Task 06 - ExpirePointsUseCase

## Objetivo
Expirar pontos vencidos.

## Entrada

- data atual

## Regras

- Apenas EARN pode expirar
- Não expirar duas vezes
- Gerar transação EXPIRE

## Tarefas

- Implementar método expirePoints(date) na conta
- Criar ExpirePointsUseCase
- Persistir alterações

## Testes

- Expira corretamente pontos vencidos
- Não expira pontos já expirados
- Saldo atualizado corretamente

## Critério de Aceite

- Processo idempotente
- Testes cobrindo múltiplos cenários