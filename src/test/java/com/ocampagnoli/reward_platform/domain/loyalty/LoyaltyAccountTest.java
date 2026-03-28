package com.ocampagnoli.reward_platform.domain.loyalty;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
