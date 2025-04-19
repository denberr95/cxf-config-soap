package com.cfx.issue.soap.runner;

import com.cfx.issue.soap.client.ClientAPR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "command-line-runner.apr.0050.enabled", havingValue = "true",
        matchIfMissing = false)
@Component
public class APR0050Runner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(APR0050Runner.class);

    private final ClientAPR client;

    public APR0050Runner(ClientAPR client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        this.client.apr0050();
    }
}
