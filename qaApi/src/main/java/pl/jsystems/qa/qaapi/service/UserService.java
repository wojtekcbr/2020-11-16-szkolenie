package pl.jsystems.qa.qaapi.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pl.jsystems.qa.qaapi.model.SimpleUser;
import pl.jsystems.qa.qaapi.model.User;
import pl.jsystems.qa.qaapi.specification.Specification;import java.util.List;


    public class UserService extends Service {

        private static final String USERS_PATH = "/5a6a58222e0000d0377a7789";
        private static final String USERS_POST_PATH = "/5a690a1b2e000051007a73cb";
        private static final String USER_BY_ID_PATH = "/5a6b69ec3100009d211b8aeb/{id}";

        public static Response returnUserResponse() {
            return getResponseV2(USERS_PATH);
        }

        public static List<User> getUsers() {
//        return returnResponseUnPack(USERS_PATH).getList("", User.class);
            return getV2UnPack(USERS_PATH).getList("", User.class);
        }

        public static SimpleUser getUser(long id) {
            return getV2UnPack(USER_BY_ID_PATH, id).getObject("", SimpleUser.class);
        }

        public static List<SimpleUser> postUser(SimpleUser user) {
            return postV2UnPack(USERS_POST_PATH, user).getList("", SimpleUser.class);
        }

        private static JsonPath getV2UnPack(String path, Object... params) {
            return getResponseV2(path, params).then().extract().body().jsonPath();
        }

        private static JsonPath postV2UnPack(String path, SimpleUser user) {
            return postResponseV2(path, user).then().extract().body().jsonPath();
        }

        private static Response getResponseV2(String path,  Object... params) {
            return getSpec()
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
