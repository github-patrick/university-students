package com.university.bdd.hooks;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerHook {

    private int port = 8888;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "http://192.168.99.100";
        RestAssured.port = this.port;
    }

}
