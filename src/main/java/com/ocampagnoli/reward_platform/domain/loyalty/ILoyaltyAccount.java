package com.ocampagnoli.reward_platform.domain.loyalty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ocampagnoli.reward_platform.domain.pointtransaction.PointTransaction;

public interface ILoyaltyAccount {
    boolean block();

    boolean unblock();

    boolean ensureActive();

    List<PointTransaction> getTransactions();

    BigDecimal getBalance();

    BigDecimal getPointsExpiringBefore(LocalDateTime date);

    void addTransaction(PointTransaction transaction);
}