package com.ocampagnoli.reward_platform.domain.loyalty;

public interface ILoyaltyAccount {
    boolean block();

    boolean unblock();

    boolean ensureActive();
}