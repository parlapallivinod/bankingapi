package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.exception.ErrorResponse;
import in.rgukt.r081247.bankingapi.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import jakarta.transaction.Transactional;
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
        String username = "user02";
        String password = "password02";
        HttpHeaders headers = new HttpHeaders();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
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
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
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
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
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
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
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
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
    }

    @Test
    public void testGetCustomerExistingUser() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user03";
        String password = "password03";
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
        String username = "user04";
        String password = "password04";
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
        String username = "user05";
        String password = "password05";
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
        String username = "user06";
        String password = "password06";
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
        assertThat(exception).isNotNull();
    }

    @Test
    public void testDeleteUserWithNonZeroBalance() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user07";
        String password = "password07";
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
        String username = "user08";
        String password = "password08";
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

        assertThat(response.getStatusCodeValue()).isEqualTo(401);
        //assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }

    @Test
    public void testDeleteUserWithZeroBalance() {
        String url = "http://localhost:" + PORT + "/v1/customers";
        System.out.println("URL: " + url);
        String username = "user09";
        String password = "password09";
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
