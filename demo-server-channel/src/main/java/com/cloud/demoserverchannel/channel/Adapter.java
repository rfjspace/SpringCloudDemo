package com.cloud.demoserverchannel.channel;

import com.cloud.demoserverchannel.service.BankAdapter;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Adapter {
    @Resource
    private ApplicationContext applicationContext;

    private ConcurrentHashMap<String, BankAdapter> concurrentHashMap = new ConcurrentHashMap();

    public BankAdapter instance(String adapterName, String adapterPath) {
        BankAdapter bankAdapter = concurrentHashMap.get(adapterName);
        if (bankAdapter == null) {
            bankAdapter = new FeignClientBuilder(applicationContext)
                    .forType(BankAdapter.class, adapterName)
                    .path(adapterPath).build();
            concurrentHashMap.put(adapterName, bankAdapter);
        }
        return concurrentHashMap.get(adapterName);
    }
}
