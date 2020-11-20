package pl.jsystems.qa.qaapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pl.jsystems.qa.qaapi.model.usersdevice.GenericUser;
import pl.jsystems.qa.qaapi.model.usersdevice.SimpleUser;
import pl.jsystems.qa.qaapi.model.usersdevice.User;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.specification.Specification;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserService extends Service {

    private static final String USERS_PATH = "/5a6a58222e0000d0377a7789";
    private static final String USERS_POST_PATH = "/5a690a1b2e000051007a73cb";
    private static final String USER_BY_ID_PATH = "/5a6b69ec3100009d211b8aeb/{id}";
    private static final String ERROR_BY_ID_PATH = "/5a690b452e000054007a73cd/{id}";
    private static final String GENERIC_USER_PATH = "/5b05bf3f3200007100ebfa04";

    public static Response returnUserResponse() {
        return getResponseV2(USERS_PATH);
    }

    public static List<User> getUsers() {
        return getV2UnPack(USERS_PATH).getList("", User.class);
    }

    public static SimpleUser getUser(long id) {
        return getV2UnPack(USER_BY_ID_PATH, id).getObject("", SimpleUser.class);
    }

    public static SimpleUser getUserByParams(Map<String, List<String>> queryParams, Object... params) {
        return getV2UnPackByQueryParam(USER_BY_ID_PATH, queryParams, params).getObject("", SimpleUser.class);
    }

    public static List<SimpleUser> postUser(SimpleUser user) {
        return postV2UnPack(USERS_POST_PATH, user).getList("", SimpleUser.class);
    }


    public static ErrorResponse getErrorResponse(long id) {
        return getV2UnPack(ERROR_BY_ID_PATH, id).getObject("", ErrorResponse.class);
    }


    public static GenericUser getGenericStringUser() throws IOException {
        return new ObjectMapper().readValue(
                getResponseV2(GENERIC_USER_PATH)
                        .then()
                        .extract()
                        .body()
                        .asInputStream(), new TypeReference<GenericUser<String>>() {}
        );
    }

    public static GenericUser getGenericIntegerUser() throws IOException {
        return new ObjectMapper().readValue(
                getResponseV2(GENERIC_USER_PATH)
                        .then()
                        .extract()
                        .body()
                        .asInputStream(), new TypeReference<GenericUser<Integer>>() {}
        );
    }


    private static JsonPath getV2UnPack(String path, Object... params) {
        return getResponseV2(path, params).then().extract().body().jsonPath();
    }

    private static JsonPath getV2UnPackByQueryParam(String path, Map<String, List<String>> queryParams, Object... params) {
        return getResponseV2ByQueryParam(path, queryParams, params).then().extract().body().jsonPath();
    }

    private static JsonPath postV2UnPack(String path, SimpleUser user) {
        return postResponseV2(path, user).then().extract().body().jsonPath();
    }

    private static Response getResponseV2(String path,  Object... params) {
        return getSpec()
                .get(path, params)
                .andReturn();
    }

    private static Response getResponseV2ByQueryParam(String path, Map<String, List<String>> queryParams, Object... params) {
        return getSpec()
                .queryParams(queryParams)
                .get(path, params)
                .andReturn();
    }

    private static RequestSpecification getSpec() {
        return RestAssured
                .given()
                .spec(Specification.requestSpecificationV2());
    }

    private static Response postResponseV2(String path, Object user) {
        return postSpec(user)
                .post(path)
                .andReturn();
    }

    private static RequestSpecification postSpec(Object user) {
        return RestAssured
                .given()
                .spec(Specification.requestSpecificationV2())
                .body(user);
    }


}