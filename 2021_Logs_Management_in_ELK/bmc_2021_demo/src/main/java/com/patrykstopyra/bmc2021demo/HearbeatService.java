package com.patrykstopyra.bmc2021demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HearbeatService {
    private static final Logger logger = LoggerFactory.getLogger(HearbeatService.class);

    @PostConstruct
    private void init() {
        new Thread(() -> {
            while (true) {
                logger.info("Hearbeat");
                synchronized (HearbeatService.this) {
                    try {
                        HearbeatService.this.wait(60000);
                    } catch (InterruptedException ex) {
                        logger.error("Heartbeat thread interrupted", ex);
                    }
                }
            }
        }).start();
    }
}
