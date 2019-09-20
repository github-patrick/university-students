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
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;

        RestAssured.given()
                .auth().basic("user","{noop}password")
                .delete("/students")
                .then().statusCode(200);

    }



}
