package testing;

import io.restassured.response.Response;
import model.Address;
import model.Company;
import model.Geo;
import model.User;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserServiceCreateTest extends UserServiceTest {

    @Test(description = "Verify creating a new user via POST request")
    public void testCreateUser() {

        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setUsername("jdoe");
        newUser.setEmail("jdoe@example.com");

        Response response = userService.createUser(newUser);

        assertThat("The user was not created successfully", response.getStatusCode(), is(201));

        assertThat("The returned user does not match", response.jsonPath().getString("name"), equalTo(newUser.getName()));
    }

    @Test(description = "Verify creating a user via POST request with all fields")
    public void testCreateUserWithAllFields() {

        Geo geo = new Geo(48.8566, 2.3522);

        Address address = new Address(
                "221B Baker Street",
                "Suite A",
                "London",
                "NW1 6XE",
                geo
        );

        Company company = new Company("TechWorld Inc.","Innovating the Future","revolutionizing industries");

        User newUser = new User(
                "John Doe",
                0,
                "johndoe",
                "johndoe@example.com",
                address,
                "+1234567890",
                "www.johndoe.com",
                company

        );

        Response response = userService.createUser(newUser);

        assertThat("The user was not created successfully", response.getStatusCode(), is(201));
        assertThat("The name was not returned correctly", response.jsonPath().getString("name"), equalTo(newUser.getName()));
        assertThat("The username was not returned correctly", response.jsonPath().getString("username"), equalTo(newUser.getUsername()));
        assertThat("The email was not returned correctly", response.jsonPath().getString("email"), equalTo(newUser.getEmail()));
        assertThat("The company name was not returned correctly", response.jsonPath().getString("company.name"), equalTo(company.getName()));
    }

}
