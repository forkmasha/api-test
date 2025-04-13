package testing;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserServiceDeleteTest extends UserServiceTest {

    @Test(description = "Verify deleting a user via DELETE request")
    public void testDeleteUser() {
        Response response = userService.deleteUser(1);
        assertThat("The user was not deleted successfully", response.getStatusCode(), is(successfulStatusCode));
    }
}
