package com.ocampagnoli.reward_platform.domain.loyalty;

public class LoyaltyAccount implements ILoyaltyAccount {

    private String id;
    private LoyaltyAccountStatus status;

    LoyaltyAccount() {
        this.status = LoyaltyAccountStatus.ACTIVE;
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

}