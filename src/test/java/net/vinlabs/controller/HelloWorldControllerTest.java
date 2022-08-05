package net.vinlabs.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class HelloWorldControllerTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void givenControllerIsRunning_whenGetCall_thenReturnApiResponse() {
        var response = client.toBlocking().retrieve("/api/v1/hello");
        assertEquals("Hello from service!", response);
    }
    @Test
    public void givenControllerIsRunning_whenGetCall_thenReturnHttpStatusAndApiResponse() {
        var response = client.toBlocking().exchange("/api/v1/hello",String.class);
        assertEquals("Hello from service!", response.getBody().get());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void givenControllerIsRunning_whenGetCall_thenReturnApiResponseFromConfig() {
        var response = client.toBlocking().retrieve("/api/v1/config");
        assertEquals("This hello world is from application yml file", response);
    }

    @Test
    public void givenControllerIsRunning_whenGetCall_thenReturnApiResponseFromTranslationConfig() {
        var response = client.toBlocking().exchange("/api/v1/translation", JsonNode.class);
        assertEquals("This hello world is from application yml file", response.getBody().get().toString());
    }

    @Test
    void helloFromTranslationEndpointReturnsContentFromConfigFile() {
        var response = client.toBlocking().exchange("/api/v1/translation", JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("{\"de\":\"Hallo Welt\",\"en\":\"Hello World\"}", response.getBody().get().toString());
    }

}
