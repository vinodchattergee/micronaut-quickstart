package net.vinlabs.service;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Singleton
@Primary
public class HelloWorldService implements MyService {
    public String helloFromService(){
        return "Hello from service!";
    }
}
