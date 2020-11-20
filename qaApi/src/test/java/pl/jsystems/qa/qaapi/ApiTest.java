package pl.jsystems.qa.qaapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.model.usersdevice.GenericUser;
import pl.jsystems.qa.qaapi.model.usersdevice.SimpleUser;
import pl.jsystems.qa.qaapi.model.usersdevice.User;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static com.google.common.truth.Truth.assertThat;

@Tag("API")
@DisplayName("ApiTest")
public class ApiTest {

    @DisplayName("First api test to mocky.io")
    @Test
    public void firstApiTest() {
        RestAssured
                .given()
                .get("http://www.mocky.io/v2/5a6b69ec3100009d211b8aeb")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Piotr"))
                .body("surname", equalTo("Kowalski"));

    }

    @DisplayName("User with devices test")
    @Test
    public void userDevices() {

        RestAssured
                .given()
                .get("http://www.mocky.io/v2/5a6a58222e0000d0377a7789")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].imie", notNullValue())
                .body("[0].imie", equalTo("Piotr"))
                .body("[0].nazwisko", notNullValue())
                .body("[0].nazwisko", equalTo("Kowalski"))
                .body("[0].device[0].type", notNullValue())
                .body("[0].device[0].type", equalTo("computer"));
//                .body("[0].device[0].device.model[0].produce", equalTo("dell"));
    }

    @DisplayName("User with devices by model")
    @Test
    public void userDeviceByModel() {
        List<User> users = RestAssured
                .given()
                .get("http://www.mocky.io/v2/5a6a58222e0000d0377a7789")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", User.class);

        System.out.println(users.toString());

        assertThat(users.get(0).imie).isEqualTo("Piotr");
    }

    @DisplayName("User with devices split response")
    @Test
    public void splitResponse() {
//        Response response = RestAssured
//                .given()
//                .get("http://www.mocky.io/v2/5a6a58222e0000d0377a7789")
//                .andReturn();


//        List<User> users = response.then().extract().body().jsonPath().getList("", User.class);
//        List<User> users = response.then().extract().body().jsonPath().getList("", User.class);

        //given
        //when
        Response response = UserService.returnUserResponse();
        List<User> users = UserService.getUsers();

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(users.get(0).imie).isEqualTo("Piotr");
        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0).nazwisko).isEqualTo("Kowalski");
        assertThat(users.get(0).device.get(0).type).isEqualTo("computer");
        assertThat(users.get(0).device.get(0).deviceModel.get(0).produce).isEqualTo("dell");
        assertThat(users.get(0).device.get(0).deviceModel.get(0).screenSize).isEqualTo(17);
        assertThat(users.get(0).device.size()).isEqualTo(2);

    }

    @DisplayName("Add user")
    @Test
    public void addUser() {
        //given
        SimpleUser user = new SimpleUser("Paweł", "Dubaj");

        //when
        List<SimpleUser> users = UserService.postUser(user);

        //then
        System.out.println(user.toString());
        assertThat(users).isEmpty();
    }

    @DisplayName("Get simple user by id.")
    @Test
    public void getSimpleUserById() {
        //given
        long id = 1;

        //when
        SimpleUser user = UserService.getUser(id);

        assertThat(user.name).isEqualTo("Piotr");
        assertThat(user.surname).isEqualTo("Kowalski");
    }

    @DisplayName("Get user by queryParam and pathVariable.")
    @Test
    public void getUserByQueryparamAndPathVariable() {

        //given
        long id = 1;

        List<String> idParams = new ArrayList<>();
        idParams.add("Paweł");
        idParams.add("Piotr");

        Map<String, List<String>> queryParams = new HashMap<>();
        queryParams.put("name", idParams);

        //when
        SimpleUser user = UserService.getUserByParams(queryParams, id);

        //then
        assertThat(user.name).isEqualTo("Piotr");
        assertThat(user.surname).isEqualTo("Kowalski");

    }

    @DisplayName("Error response for incorrect id.")
    @Test
    public void errorResponse() {
        //given
        long id = 1;

        //when
        ErrorResponse errorResponse = UserService.getErrorResponse(id);

        //then
        assertThat(errorResponse.error.errorCode).isEqualTo(400);
        assertThat(errorResponse.error.validationError).isEqualTo("invalid_email");
        assertThat(errorResponse.error.message).isEqualTo("your email is invalid");
    }

    @DisplayName("Generic type , String")
    @Test
    public void genericString() throws IOException {
        //
        GenericUser<String> user = UserService.getGenericStringUser();

        assertThat(user.id).isEqualTo("1");
    }

    @DisplayName("Generic type , String")
    @Test
    public void genericInteger() throws IOException {
        //
        GenericUser<Integer> user = UserService.getGenericIntegerUser();

        assertThat(user.id).isEqualTo(1);
    }


}