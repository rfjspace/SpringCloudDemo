package com.cloud.demoservertwo.controller;

import com.cloud.demoservercommon.model.InMoney;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server-one")
public class ServerOneController {

    @Autowired
    private EurekaClient eurekaClient;

    @PostMapping("/one")
    public List<InstanceInfo> one(){
        List<InstanceInfo> instanceInfos = eurekaClient.getInstancesByVipAddress("demo-server-one",false);
        return instanceInfos;
    }

    @PostMapping("/two")
    public String two(@RequestBody InMoney im) throws Exception {
        return "/server-one/two:".concat(im.toString());
    }
}
