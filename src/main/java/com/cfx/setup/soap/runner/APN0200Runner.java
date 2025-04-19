package com.cfx.setup.soap.runner;

import com.cfx.setup.soap.client.ClientAPN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "command-line-runner.apn.0200.enabled", havingValue = "true",
        matchIfMissing = false)
@Component
public class APN0200Runner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(APN0200Runner.class);

    private final ClientAPN client;

    public APN0200Runner(ClientAPN client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        this.client.apn0200();
    }
}
