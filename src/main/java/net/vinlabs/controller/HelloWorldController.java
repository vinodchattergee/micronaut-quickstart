package net.vinlabs.controller;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import net.vinlabs.service.HelloWorldTransactionConfiguration;
import net.vinlabs.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/v1")
public class HelloWorldController {
    // http://localhost:8080/api/v1/hello

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    //@Inject
    private final MyService service;
    @Property(name = "hello.world.message")
    private String helloFromConfig;

    private final HelloWorldTransactionConfiguration transactionConfiguration;

    public HelloWorldController(MyService service, HelloWorldTransactionConfiguration transactionConfiguration) {
        this.service = service;
        this.transactionConfiguration = transactionConfiguration;
    }

    @Get(value = "/hello", produces = MediaType.TEXT_PLAIN)
    public String helloWorld() {
        LOGGER.info(String.format("Received call and thus responding => %s",service.helloFromService()));

        return service.helloFromService();
    }

    @Get(value = "/config", produces = MediaType.TEXT_PLAIN)
    public String helloWorldFromConfig() {
        LOGGER.info(String.format("Received call and thus responding => %s",helloFromConfig));

        return helloFromConfig;
    }

    @Get(value = "/translation", produces = MediaType.APPLICATION_JSON)
    public HelloWorldTransactionConfiguration translationHelloWorldFromConfig() {
        LOGGER.info(String.format("Received call and thus responding => %s",transactionConfiguration));
        return transactionConfiguration;
    }
}
