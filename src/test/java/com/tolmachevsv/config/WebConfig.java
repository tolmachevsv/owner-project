package com.tolmachevsv.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${launch}.properties"})
public interface WebConfig extends Config {

    @Key("browserName")
    String browserName();

    @Key("browserVersion")
    Double browserVersion();

    @Key("baseUrl")
    String baseUrl();

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("remoteUrl")
    String remoteUrl();
}
