package com.tolmachevsv.tests;

import com.codeborne.selenide.Configuration;
import com.tolmachevsv.config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("remote_test")
public class RemoteLaunchTest extends TestBase {

    @Test
    public void openRemotely() {
        System.setProperty("launch", "remote");
        System.out.println(System.getProperty("launch"));

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.remote = format("https://%s:%s@%s", config.login(), config.password(), config.remoteUrl());
        assertThat(config.browserName()).isEqualTo("firefox");
        assertThat(config.browserVersion()).isEqualTo(99.0);

        open(config.baseUrl());
        $("h1").shouldHave(text("Let’s build from here"));
    }
}
