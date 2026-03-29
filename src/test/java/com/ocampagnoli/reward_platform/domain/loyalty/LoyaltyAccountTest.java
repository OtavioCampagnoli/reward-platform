package com.ocampagnoli.reward_platform.domain.loyalty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ocampagnoli.reward_platform.domain.pointtransaction.PointTransaction;
import com.ocampagnoli.reward_platform.domain.pointtransaction.PointTransactionType;

@SpringBootTest
public class LoyaltyAccountTest {

    @Test
    void shouldBlockAnActiveLoyaltyAccount() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        // When
        boolean result = loyaltyAccount.block();
        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldShowAnErrorWhenTryingToBlockAnAlreadyBlockedLoyaltyAccount() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.block();
        // When
        LoyaltyAccountException exception = Assertions.assertThrows(LoyaltyAccountException.class,
                loyaltyAccount::block);
        // Then
        Assertions.assertEquals("Loyalty account is already blocked", exception.getMessage());
    }

    @Test
    void shouldUnblockABlockedLoyaltyAccount() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.block();
        // When
        boolean result = loyaltyAccount.unblock();
        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldShowAnErrorWhenTryingToUnblockAnAlreadyActiveLoyaltyAccount() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        // When
        LoyaltyAccountException exception = Assertions.assertThrows(LoyaltyAccountException.class,
                loyaltyAccount::unblock);
        // Then
        Assertions.assertEquals("Loyalty account is already active", exception.getMessage());
    }

    @Test
    void shouldEnsureActiveLoyaltyAccount() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        // When
        boolean result = loyaltyAccount.ensureActive();
        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldShowAnErrorWhenTryingToEnsureActiveABlockedLoyaltyAccount() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.block();
        // When
        LoyaltyAccountException exception = Assertions.assertThrows(LoyaltyAccountException.class,
                loyaltyAccount::ensureActive);
        // Then
        Assertions.assertEquals("Loyalty account is not active", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate balance correctly considering EARN, REDEEM and EXPIRE transactions")
    void shouldCalculateBalanceCorrectly() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(100), PointTransactionType.EARN,
                LocalDateTime.now(), LocalDateTime.now().plusDays(30)));
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(30), PointTransactionType.REDEEM,
                LocalDateTime.now(), null));
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(20), PointTransactionType.EXPIRE,
                LocalDateTime.now(), null));
        // When
        BigDecimal balance = loyaltyAccount.getBalance();
        // Then
        Assertions.assertEquals(BigDecimal.valueOf(50), balance);
    }

    @Test
    @DisplayName("Should not include expired EARN transactions in balance calculation")
    void shouldNotIncludeExpiredEarnTransactionsInBalance() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(100), PointTransactionType.EARN,
                LocalDateTime.now().minusDays(40), LocalDateTime.now().minusDays(10)));
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(50), PointTransactionType.EARN,
                LocalDateTime.now(), LocalDateTime.now().plusDays(30)));
        // When
        BigDecimal balance = loyaltyAccount.getBalance();
        // Then
        Assertions.assertEquals(BigDecimal.valueOf(50), balance);
    }

    @Test
    @DisplayName("Should return correct points expiring before a given date")
    void shouldReturnPointsExpiringBefore() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(100), PointTransactionType.EARN,
                LocalDateTime.now(), LocalDateTime.now().plusDays(10)));
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(50), PointTransactionType.EARN,
                LocalDateTime.now(), LocalDateTime.now().plusDays(20)));
        loyaltyAccount.addTransaction(new PointTransaction(BigDecimal.valueOf(30), PointTransactionType.EARN,
                LocalDateTime.now(), LocalDateTime.now().plusDays(5)));
        // When
        BigDecimal expiringPoints = loyaltyAccount.getPointsExpiringBefore(LocalDateTime.now().plusDays(15));
        // Then
        Assertions.assertEquals(BigDecimal.valueOf(130), expiringPoints);
    }

    @Test
    @DisplayName("Should initialize loyalty account with given transactions and calculate balance accordingly")
    void shouldInitializeLoyaltyAccountWithGivenTransactions() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount(List.of(
                new PointTransaction(BigDecimal.valueOf(100), PointTransactionType.EARN, LocalDateTime.now(),
                        LocalDateTime.now().plusDays(30)),
                new PointTransaction(BigDecimal.valueOf(30), PointTransactionType.REDEEM, LocalDateTime.now(), null),
                new PointTransaction(BigDecimal.valueOf(20), PointTransactionType.EXPIRE, LocalDateTime.now(), null)));
        // When
        BigDecimal balance = loyaltyAccount.getBalance();
        // Then
        Assertions.assertEquals(BigDecimal.valueOf(50), balance);
    }

    @Test
    @DisplayName("Should not allow adding null transaction")
    void shouldNotAllowAddingNullTransaction() {
        // Given
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        // When
        LoyaltyAccountException exception = Assertions.assertThrows(LoyaltyAccountException.class,
                () -> loyaltyAccount.addTransaction(null));
        // Then
        Assertions.assertEquals("Transaction cannot be null", exception.getMessage());
    }
}
