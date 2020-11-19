package pl.jsystems.qa.qaapi.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.jsystems.qa.qaapi.configuration.ApiConfig;

public class Specification {

    public static final String BASE_PATH_V2 = "v2";

    public static RequestSpecification requestSpecificationV2() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(ApiConfig.BASE_URL)
                .setBasePath(BASE_PATH_V2)
                .build();
    }

    public static RequestSpecification requestSpecificationAuth() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", ApiConfig.API_KEY)
                .setBaseUri(ApiConfig.BASE_URL)
                .setBasePath(BASE_PATH_V2)
                .build();
    }
}
