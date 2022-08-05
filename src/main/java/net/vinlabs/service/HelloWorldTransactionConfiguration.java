package net.vinlabs.service;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("hello.world.translation")
public interface HelloWorldTransactionConfiguration {
    @NotBlank
    String getDe();
    @NotBlank
    String getEn();
}
