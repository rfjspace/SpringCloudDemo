package com.cloud.demoserverchannel.service;

import com.cloud.demoservercommon.model.InMoney;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface BankAdapter {

    @PostMapping("/one")
    String one();

    @PostMapping("/two")
    String two(@RequestBody InMoney im);
}
