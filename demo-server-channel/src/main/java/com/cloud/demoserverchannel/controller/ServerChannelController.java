package com.cloud.demoserverchannel.controller;

import com.cloud.demojarcommon.annotation.IgnorCommonInterceptor;
import com.cloud.demoserverchannel.channel.Adapter;
import com.cloud.demoserverchannel.model.ChannelReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server-channel")
public class ServerChannelController {

    @Autowired
    private Adapter adapter;

    @PostMapping("/one")
    public String one(@RequestBody ChannelReq.Adapter req) {
        return adapter.instance(req.getAdapterName(), req.getAdapterPath()).one();
    }

    @PostMapping("/two")
    @IgnorCommonInterceptor
    public String two(@RequestBody ChannelReq.Adapter req) {
        return adapter.instance(req.getAdapterName(), req.getAdapterPath()).two(req.getIm());
    }
}
