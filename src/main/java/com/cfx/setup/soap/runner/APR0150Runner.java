package com.cfx.setup.soap.runner;

import com.cfx.setup.soap.client.ClientAPR;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "command-line-runner.apr.0150.enabled", havingValue = "true",
        matchIfMissing = false)
@Component
public class APR0150Runner implements CommandLineRunner {

    private final ClientAPR client;

    public APR0150Runner(ClientAPR client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        this.client.apr0150();
    }
}
