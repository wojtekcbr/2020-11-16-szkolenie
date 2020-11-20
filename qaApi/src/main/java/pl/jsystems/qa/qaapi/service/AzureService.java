package pl.jsystems.qa.qaapi.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pl.jsystems.qa.qaapi.model.azure.AzureAuthor;
import pl.jsystems.qa.qaapi.model.usersdevice.User;
import pl.jsystems.qa.qaapi.specification.Specification;

import java.util.List;

public class AzureService {

    private static final String AUTHORS = "/Authors";

    public static List<AzureAuthor> getAzureAuthors() {
        return getAuthorsUnPack(AUTHORS).getList("", AzureAuthor.class);
    }

    private static JsonPath getAuthorsUnPack(String path) {
        return getAzureAuthors(path).then().extract().body().jsonPath();
    }

    private static Response getAzureAuthors(String path) {
        return getAzureSpec()
                .get(path)
                .andReturn();
    }

    private static RequestSpecification getAzureSpec() {
        return RestAssured
                .given()
                .spec(Specification.azureSpecification());
    }
}
