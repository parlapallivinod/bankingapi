package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.exception.ErrorResponse;
import in.rgukt.r081247.bankingapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CustomerControllerIntegrationTests {
    @LocalServerPort
    private int PORT;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRegisterCustomerNewUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/registration";
        System.out.println("URL: " + url);
        String username = "user04";
        String password = "password04";
        HttpHeaders headers = new HttpHeaders();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        //ResponseEntity<String> response = template.getForEntity(url, String.class);
        ResponseEntity<User> response = template.exchange(url, HttpMethod.POST, entity, User.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getUsername()).isEqualTo(username);
        assertThat(response.getBody().getPassword()).isEqualTo(password);
    }

    @Test
    public void testRegisterCustomerExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/registration";
        System.out.println("URL: " + url);
        String username = "user01";
        String password = "password01";
        HttpHeaders headers = new HttpHeaders();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        //ResponseEntity<String> response = template.getForEntity(url, String.class);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
        //assertThat(response.getBody().getPassword()).isEqualTo(password);
    }

    @Test
    public void testRegisterCustomerEmptyAttributes() {
        String url = "http://localhost:" + PORT + "/v1/customers/registration";
        System.out.println("URL: " + url);
        String username = "";
        String password = "";
        HttpHeaders headers = new HttpHeaders();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        //ResponseEntity<String> response = template.getForEntity(url, String.class);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
        //assertThat(response.getBody().getPassword()).isEqualTo(password);
    }

    @Test
    public void testRegisterCustomerLength4Attributes() {
        String url = "http://localhost:" + PORT + "/v1/customers/registration";
        System.out.println("URL: " + url);
        String username = "abcd";
        String password = "abcd";
        HttpHeaders headers = new HttpHeaders();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        //ResponseEntity<String> response = template.getForEntity(url, String.class);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
        //assertThat(response.getBody().getPassword()).isEqualTo(password);
    }

    @Test
    public void testRegisterCustomerLength17Attributes() {
        String url = "http://localhost:" + PORT + "/v1/customers/registration";
        System.out.println("URL: " + url);
        String username = "abcdefghijklmnopq";
        String password = "abcdefghijklmnopq";
        HttpHeaders headers = new HttpHeaders();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        //ResponseEntity<String> response = template.getForEntity(url, String.class);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
        //assertThat(response.getBody().getPassword()).isEqualTo(password);
    }

    @Test

    public void testGetCustomerExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user01";
        String password = "password01";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<User> response = template.exchange(url, HttpMethod.GET, entity, User.class);
        System.out.println("Headers: " + headers);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getUsername()).isEqualTo(username);
        assertThat(response.getBody().getPassword()).isEqualTo(password);
    }

    @Test
    public void testGetCustomerNonExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Object> response = template.exchange(url, HttpMethod.GET, entity, Object.class);
        System.out.println("Headers: " + headers);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void testUpdateUserPasswordExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user01";
        String password = "password01";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password + "new");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<User> response = template.exchange(url, HttpMethod.PUT, entity, User.class);
        System.out.println("Headers: " + headers);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getUsername()).isEqualTo(username);
        assertThat(response.getBody().getPassword()).isEqualTo(password + "new");
    }

    @Test
    public void testUpdateUserPasswordNonExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password + "new");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        Exception exception = assertThrows(Exception.class, () -> {
            ResponseEntity<Object> response = template.exchange(url, HttpMethod.PUT, entity, Object.class);
            System.out.println("Headers: " + headers);
            System.out.println("Response Status: " + response.getStatusCodeValue());
            System.out.println("Response Body: " + response.getBody());
        });
        System.out.println("Exception: " + exception);
    }

    @Test
    public void testDeleteUserWithNonZeroBalance() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user02";
        String password = "password02";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.DELETE, entity, ErrorResponse.class);
        System.out.println("Headers: " + headers);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }

    @Test
    public void testDeleteUserNonExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user02";
        String password = "password00000000";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Object> response = template.exchange(url, HttpMethod.DELETE, entity, Object.class);
        System.out.println("Headers: " + headers);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        //assertThat(response.getStatusCodeValue()).isEqualTo(400);
        //assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }

    @Test
    public void testDeleteUserWithZeroBalance() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user05";
        String password = "password05";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Object> response = template.exchange(url, HttpMethod.DELETE, entity, Object.class);
        System.out.println("Headers: " + headers);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        //assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }
}
