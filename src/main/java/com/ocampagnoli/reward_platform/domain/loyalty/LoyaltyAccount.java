package com.ocampagnoli.reward_platform.domain.loyalty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ocampagnoli.reward_platform.domain.pointtransaction.PointTransaction;
import com.ocampagnoli.reward_platform.domain.pointtransaction.PointTransactionType;

public class LoyaltyAccount implements ILoyaltyAccount {

    private String id;
    private LoyaltyAccountStatus status;
    private final List<PointTransaction> transactions;

    LoyaltyAccount() {
        this.transactions = new ArrayList<>();
        this.status = LoyaltyAccountStatus.ACTIVE;
    }

    LoyaltyAccount(List<PointTransaction> pointTransactions) {
        this.status = LoyaltyAccountStatus.ACTIVE;
        this.transactions = pointTransactions;
    }

    @Override
    public boolean block() {
        if (this.status == LoyaltyAccountStatus.BLOCKED) {
            throw new LoyaltyAccountException("Loyalty account is already blocked");
        }
        this.status = LoyaltyAccountStatus.BLOCKED;
        return true;
    }

    @Override
    public boolean unblock() {
        if (this.status == LoyaltyAccountStatus.ACTIVE) {
            throw new LoyaltyAccountException("Loyalty account is already active");
        }
        this.status = LoyaltyAccountStatus.ACTIVE;
        return true;
    }

    @Override
    public boolean ensureActive() {
        if (this.status != LoyaltyAccountStatus.ACTIVE) {
            throw new LoyaltyAccountException("Loyalty account is not active");
        }
        return true;
    }

    @Override
    public BigDecimal getBalance() {
        return transactions.stream()
                .filter(this::isValidForBalance)
                .map(tx -> {
                    BigDecimal amount = tx.getAmount();
                    if (tx.getType().equals(PointTransactionType.REDEEM)
                            || tx.getType().equals(PointTransactionType.EXPIRE)) {
                        amount = amount.negate();
                    }
                    return amount;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isValidForBalance(PointTransaction tx) {
        if (tx.getType().equals(PointTransactionType.EARN) && tx.getExpirationDate() != null) {
            return tx.getExpirationDate().isAfter(LocalDateTime.now());
        }
        return true;
    }

    @Override
    public List<PointTransaction> getTransactions() {
        return transactions;
    }

    @Override
    public BigDecimal getPointsExpiringBefore(LocalDateTime date) {
        List<PointTransaction> expiringTransactions = getTransactions().stream()
                .filter(tx -> tx.getType().equals(PointTransactionType.EARN)
                        && tx.getExpirationDate() != null
                        && tx.getExpirationDate().isBefore(date))
                .toList();
        return expiringTransactions.stream()
                .map(PointTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void addTransaction(PointTransaction transaction) {
        if (transaction == null) {
            throw new LoyaltyAccountException("Transaction cannot be null");
        }
        if (transaction.getType().equals(PointTransactionType.REDEEM)
                || transaction.getType().equals(PointTransactionType.EXPIRE)) {
            BigDecimal newBalance = getBalance().subtract(transaction.getAmount());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new LoyaltyAccountException("Insufficient points for this transaction");
            }
        }
        getTransactions().add(transaction);
    }

}