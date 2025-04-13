package testing;

import io.restassured.response.Response;
import model.User;
import org.testng.annotations.Test;
import services.UserService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserServiceGetTest extends UserServiceTest {

    @Test(description = "Verify the HTTP status code is 200 OK")
    public void testStatusCode() {
        Response response = userService.getUsers();
        assertThat("Status code is not 200 OK", response.getStatusCode(), is(successfulStatusCode));
    }

    @Test(description = "Verify the HTTP response header contains content-type application/json")
    public void testHeader() {
        Response response = userService.getUsers();
        String expectedHeader = "application/json; charset=utf-8";
        String headerName = "Content-Type";

        assertThat("Header Content-Type is missing", response.getHeaders().hasHeaderWithName(headerName), is(true));
        assertThat("Header Content-Type value is incorrect", response.getHeader(headerName), equalTo(expectedHeader));
    }

    @Test(description = "Verify the HTTP response body contains an array with 10 users")
    public void testHttpResponseBody() {
        List<User> users = userService.getAllUsers();
        int expectedUsers = 10;

        assertThat("The response does not contain 10 users", users.size(), is(expectedUsers));
    }
}