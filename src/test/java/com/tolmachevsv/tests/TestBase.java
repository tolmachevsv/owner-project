package com.tolmachevsv.tests;

import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeEach
    public void setUp() {
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
    }
}
