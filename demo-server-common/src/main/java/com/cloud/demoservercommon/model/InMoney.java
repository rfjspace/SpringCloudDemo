package com.cloud.demoservercommon.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InMoney extends BaseModel {
    @ModelField(order = 1, type = TypeEnum.NORMAL, length = 5)
    private String firmId;
    @ModelField(order = 2, type = TypeEnum.DECIMAL, length = 5)
    private String amount;
    @ModelField(order = 3, type = TypeEnum.CLAZZ)
    private InMoney1 inMoney1;
}
