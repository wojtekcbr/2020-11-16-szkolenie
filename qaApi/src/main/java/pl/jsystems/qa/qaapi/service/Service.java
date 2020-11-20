package pl.jsystems.qa.qaapi.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Service {


    public static Response returnResponse(String host) {
        return RestAssured
                .given()
                .get(host)
                .andReturn();
    }
    public static JsonPath returnResponseUnPack(String host) {
        return returnResponse(host).then().extract().body().jsonPath();
    }
}
