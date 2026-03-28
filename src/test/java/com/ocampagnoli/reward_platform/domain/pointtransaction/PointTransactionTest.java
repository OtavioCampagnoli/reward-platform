package com.ocampagnoli.reward_platform.domain.pointtransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointTransactionTest {

    @Test
    @DisplayName("Should create a valid point transaction")
    void shouldCreateValidPointTransaction() {
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.EARN,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));
    }

    @Test
    @DisplayName("Should throw an exception when creating a point transaction with null amount")
    void shouldThrowExceptionWhenAmountIsNull() {
        try {
            new PointTransaction(
                    null,
                    PointTransactionType.EARN,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Amount cannot be null");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating a point transaction with negative amount")
    void shouldThrowExceptionWhenAmountIsNegative() {
        try {
            new PointTransaction(
                    new BigDecimal("-100"),
                    PointTransactionType.EARN,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Amount must be a positive value");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating a point transaction with null type")
    void shouldThrowExceptionWhenTypeIsNull() {
        try {
            new PointTransaction(
                    new BigDecimal("100"),
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Type cannot be null");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating a point transaction with null createdAt")
    void shouldThrowExceptionWhenCreatedAtIsNull() {
        try {
            new PointTransaction(
                    new BigDecimal("100"),
                    PointTransactionType.EARN,
                    null,
                    LocalDateTime.now().plusDays(30));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("CreatedAt cannot be null");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating an EARN point transaction with null expiration date")
    void shouldThrowExceptionWhenExpirationDateIsNullForEarn() {
        try {
            new PointTransaction(
                    new BigDecimal("100"),
                    PointTransactionType.EARN,
                    LocalDateTime.now(),
                    null);
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Expiration Date cannot be null for EARN transactions");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating an EARN point transaction with expiration date before createdAt")
    void shouldThrowExceptionWhenExpirationDateIsBeforeCreatedAtForEarn() {
        try {
            new PointTransaction(
                    new BigDecimal("100"),
                    PointTransactionType.EARN,
                    LocalDateTime.now(),
                    LocalDateTime.now().minusDays(1));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Expiration Date must be after Created At");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating a REDEEM point transaction with non null expiration date")
    void shouldThrowExceptionWhenExpirationDateIsNotNullForRedeem() {
        try {
            new PointTransaction(
                    new BigDecimal("100"),
                    PointTransactionType.REDEEM,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Expiration Date must be null for REDEEM transaction type");
        }
    }

    @Test
    @DisplayName("Should throw an exception when creating a ADJUSTMENT point transaction with non null expiration date")
    void shouldThrowExceptionWhenExpirationDateIsNotNullForAdjustment() {
        try {
            new PointTransaction(
                    new BigDecimal("100"),
                    PointTransactionType.ADJUSTMENT,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30));
        } catch (PointTransactionException e) {
            assert e.getMessage().equals("Expiration Date must be null for ADJUSTMENT transaction type");
        }
    }

    @Test
    @DisplayName("Should create a valid REDEEM point transaction")
    void shouldCreateValidRedeemPointTransaction() {
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.REDEEM,
                LocalDateTime.now(),
                null);
    }

    @Test
    @DisplayName("Should create a valid ADJUSTMENT point transaction")
    void shouldCreateValidAdjustmentPointTransaction() {
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.ADJUSTMENT,
                LocalDateTime.now(),
                null);
    }

    // Getters tests
    @Test
    @DisplayName("Should return correct amount")
    void shouldReturnCorrectAmount() {
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.EARN,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));
        assert pointTransaction.getAmount().equals(new BigDecimal("100"));
    }

    @Test
    @DisplayName("Should return correct type")
    void shouldReturnCorrectType() {
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.EARN,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));
        assert pointTransaction.getType().equals(PointTransactionType.EARN);
    }

    @Test
    @DisplayName("Should return correct createdAt")
    void shouldReturnCorrectCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.EARN,
                now,
                now.plusDays(30));
        assert pointTransaction.getCreatedAt().equals(now);
    }

    @Test
    @DisplayName("Should return correct expiration date")
    void shouldReturnCorrectExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusDays(30);
        PointTransaction pointTransaction = new PointTransaction(
                new BigDecimal("100"),
                PointTransactionType.EARN,
                now,
                expirationDate);
        assert pointTransaction.getExpirationDate().equals(expirationDate);
    }

}
