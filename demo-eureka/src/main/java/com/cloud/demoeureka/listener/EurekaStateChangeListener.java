package com.cloud.demoeureka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {

    private static Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        logger.warn("{}: 已下线！", event.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        logger.warn("{}: 已上线！", event.getInstanceInfo().getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.warn("{}: 已续约！", event.getInstanceInfo().getAppName());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.warn("注册中心启动！");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.warn("EurekaServer 启动！");
    }
}
