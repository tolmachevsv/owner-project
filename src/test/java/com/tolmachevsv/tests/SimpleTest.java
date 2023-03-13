package com.tolmachevsv.tests;

import com.codeborne.selenide.Configuration;
import com.tolmachevsv.config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleTest {

    @BeforeEach
    public void setUp() {
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
    }

    @Tag("local_test")
    @Test
    public void openLocally() {
        System.setProperty("launch", "local");

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        assertThat(config.browserName()).isEqualTo("chrome");
        assertThat(config.browserVersion()).isEqualTo(100.0);

        open(config.baseUrl());
        $("[alt=Google]").shouldBe(visible);
    }

    @Tag("remote_test")
    @Test
    public void openRemotely() {
        System.setProperty("launch", "remote");

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.remote = format("https://%s:%s@%s", config.login(), config.password(), config.remoteUrl());
        assertThat(config.browserName()).isEqualTo("firefox");
        assertThat(config.browserVersion()).isEqualTo(99.0);

        open(config.baseUrl());
        $("h1").shouldHave(text("Let’s build from here"));
    }
}
