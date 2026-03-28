package com.ocampagnoli.reward_platform.domain.pointtransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IPointTransaction {
    BigDecimal getAmount();

    PointTransactionType getType();

    LocalDateTime getCreatedAt();

    LocalDateTime getExpirationDate();

    boolean validate();
}
