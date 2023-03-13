package com.tolmachevsv.tests;

import com.tolmachevsv.config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("local_test")
public class LocalLaunchTest extends TestBase {

    @Test
    public void openLocally() {
        System.getProperty("launch", "local");

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        assertThat(config.browserName()).isEqualTo("chrome");
        assertThat(config.browserVersion()).isEqualTo(100.0);

        open(config.baseUrl());
        $("[alt=Google]").shouldBe(visible);
    }
}
