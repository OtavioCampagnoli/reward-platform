# Task 02 - PointTransaction Domain

## Objetivo
Criar entidade imutável para representar movimentações de pontos.

## Tipos

- EARN
- REDEEM
- EXPIRE
- ADJUSTMENT

## Regras

- amount não pode ser zero
- tipo obrigatório
- createdAt obrigatório
- apenas EARN possui expirationDate

## Tarefas

- Criar enum PointTransactionType
- Criar classe PointTransaction
- Garantir imutabilidade
- Validar invariantes no construtor

## Testes Unitários

- Não permite amount zero
- Não permite tipo nulo
- REDEEM não pode ter expirationDate
- EARN deve permitir expirationDate

## Critério de Aceite

- Classe imutável
- Testes cobrindo todas invariantes