package com.ocampagnoli.reward_platform.domain.pointtransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PointTransaction implements IPointTransaction {
    private final BigDecimal amount;
    private final PointTransactionType type;
    private final LocalDateTime createdAt;
    private final LocalDateTime expirationDate;

    public PointTransaction(BigDecimal amount, PointTransactionType type, LocalDateTime createdAt,
            LocalDateTime expirationDate) {
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
        this.validate();
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public PointTransactionType getType() {
        return type;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean validate() {
        if (amount == null) {
            throw new PointTransactionException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PointTransactionException("Amount must be a positive value");
        }
        if (type == null) {
            throw new PointTransactionException("Type cannot be null");
        }
        if (createdAt == null) {
            throw new PointTransactionException("CreatedAt cannot be null");
        }
        if (type.equals(PointTransactionType.EARN)) {
            if (expirationDate == null) {
                throw new PointTransactionException("Expiration Date cannot be null for EARN transactions");
            }
            if (expirationDate.isBefore(createdAt)) {
                throw new PointTransactionException("Expiration Date must be after Created At");
            }
        } else {
            if (expirationDate != null) {
                throw new PointTransactionException(
                        "Expiration Date must be null for " + type.toString() + " transaction type");
            }
        }
        return true;
    }
}