package com.dimasguilherme;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class ProductResourceTest {
    @Test
    public void checkIfApiProductIsActive(){
        RestAssured
            .given().get("/?page=1").then().statusCode(200);
    }
}
