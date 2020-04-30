package com.cloud.demoservercommon.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class InMoney {
    private Integer firmId;
    private BigDecimal amount;
}
