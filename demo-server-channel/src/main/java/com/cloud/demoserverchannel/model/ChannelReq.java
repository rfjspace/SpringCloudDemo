package com.cloud.demoserverchannel.model;

import com.cloud.demoservercommon.model.InMoney;
import lombok.Data;
import lombok.experimental.Accessors;

public class ChannelReq {
    @Data
    @Accessors(chain = true)
    public static class Adapter {
        private String adapterName;
        private String adapterPath;
        private InMoney im;
    }
}
