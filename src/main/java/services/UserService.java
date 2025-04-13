package services;

import groovy.transform.ASTTest;
import io.restassured.response.Response;
import model.User;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService {

    private String endpoint = "https://jsonplaceholder.typicode.com/users";

    public Response getUsers() {
        return given()
                .when()
                .get(endpoint);
    }

    public List<User> getAllUsers() {
        return getUsers()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("", User.class);
    }
    public Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(endpoint);
    }

    public Response updateUser(int id, User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .put(endpoint + "/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .when()
                .delete(endpoint + "/" + id);
    }

}