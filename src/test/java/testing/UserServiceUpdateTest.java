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

public class UserServiceUpdateTest extends UserServiceTest {

    @Test(description = "Verify updating an existing user via PUT request")
    public void testUpdateUser() {
        User updatedUser = new User();
        updatedUser.setName("Jane Doe");
        updatedUser.setUsername("janed");
        updatedUser.setEmail("janed@example.com");

        Response response = userService.updateUser(1, updatedUser);
        assertThat("The user was not updated successfully", response.getStatusCode(), is(successfulStatusCode));
        assertThat("The returned user was not updated correctly", response.jsonPath().getString("name"), equalTo(updatedUser.getName()));
    }

    @Test(description = "Verify updating a user via PUT request with all fields")
    public void testUpdateUserWithAllFields() {

        Geo geo = new Geo(37.7749, -122.4194);

        Address updatedAddress = new Address(
                "1600 Amphitheatre Parkway",
                "Suite B",
                "Mountain View",
                "94043",
                geo
        );

        Company updatedCompany = new Company("FutureTech Labs", "Shaping Tomorrow", "empowering disruptions");
        User updatedUser = new User(
                "Jane Smith",
                1,
                "janesmith",
                "jane.smith@example.com",
                updatedAddress,
                "+0987654321",
                "www.janesmith.com",
                updatedCompany
        );
        Response response = userService.updateUser(1, updatedUser);

        assertThat("The user was not updated successfully", response.getStatusCode(), is(200));
        assertThat("The name was not updated correctly", response.jsonPath().getString("name"), equalTo(updatedUser.getName()));
        assertThat("The address city was not updated correctly", response.jsonPath().getString("address.city"), equalTo(updatedAddress.getCity()));
        assertThat("The website was not updated correctly", response.jsonPath().getString("website"), equalTo(updatedUser.getWebsite()));
    }
}

