package pl.jsystems.qa.qaapi.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pl.jsystems.qa.qaapi.specification.Specification;

public class Service {

    public static final String BASE_PATH_V2 = "v2";

    public static Response returnResponse(String host) {
        return RestAssured
                .given()
                .get(host)
                .andReturn();
    }

    public static JsonPath returnResponseUnPack(String host) {
        return returnResponse(host).then().extract().body().jsonPath();
    }

    public static Response responseV2(String path){
        return RestAssured
                .given()
                .spec(Specification.requestSpecificationV2())
                .get(path)
                .andReturn();
    }
}
