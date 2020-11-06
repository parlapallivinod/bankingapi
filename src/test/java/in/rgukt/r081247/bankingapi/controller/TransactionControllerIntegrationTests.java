package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.exception.ErrorResponse;
import in.rgukt.r081247.bankingapi.model.Transaction;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.model.type.TransactionStatus;
import in.rgukt.r081247.bankingapi.model.type.TransactionType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
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

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TransactionControllerIntegrationTests {
    @LocalServerPort
    private int PORT;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testPerformTransactionInvalidUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user10";
        String password = "password10";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.DEPOSIT);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);

        Exception exception = assertThrows(Exception.class, () -> {
            ResponseEntity < Transaction > response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
            System.out.println("Response Status: " + response.getStatusCodeValue());
            System.out.println("Response Body: " + response.getBody());
        });
        System.out.println("Exception: " + exception);
        assertThat(exception).isNotNull();
    }

    @Test
    public void testPerformTransactionZeroAmount() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(0L);
        transaction.setType(TransactionType.DEPOSIT);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
    }

    @Test
    public void testPerformTransactionNegativeAmount() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(-100L);
        transaction.setType(TransactionType.DEPOSIT);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
    }

    @Test
    public void testPerformTransactionInvalidTransactionType() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(null);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Request Parameters Validation Error");
    }

    @Test
    public void testPerformTransactionDepositValidUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.DEPOSIT);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<Transaction> response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getStatus()).isEqualTo(TransactionStatus.SUCCESS);
    }

    @Test
    public void testPerformTransactionDepositValidToUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.DEPOSIT);
        User toUser = new User();
        toUser.setUsername("user12");
        transaction.setToUser(toUser);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<Transaction> response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getStatus()).isEqualTo(TransactionStatus.SUCCESS);
    }

    @Test
    public void testPerformTransactionDepositInvalidToUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.DEPOSIT);
        User toUser = new User();
        toUser.setUsername("user10");
        transaction.setToUser(toUser);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }

    @Test
    public void testPerformTransactionWithdrawValidUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.WITHDRAW);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<Transaction> response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getStatus()).isEqualTo(TransactionStatus.SUCCESS);
    }

    @Test
    public void testPerformTransactionWithdrawInvalidAmount() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100000L);
        transaction.setType(TransactionType.WITHDRAW);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<Transaction> response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getStatus()).isEqualTo(TransactionStatus.FAILURE);
    }

    @Test
    public void testPerformTransactionTransferValidToUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.TRANSFER);
        User toUser = new User();
        toUser.setUsername("user12");
        transaction.setToUser(toUser);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<Transaction> response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getStatus()).isEqualTo(TransactionStatus.SUCCESS);
    }

    @Test
    public void testPerformTransactionTransferInvalidAmount() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100000L);
        transaction.setType(TransactionType.TRANSFER);
        User toUser = new User();
        toUser.setUsername("user12");
        transaction.setToUser(toUser);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<Transaction> response = template.exchange(url, HttpMethod.POST, entity, Transaction.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getStatus()).isEqualTo(TransactionStatus.FAILURE);
    }

    @Test
    public void testPerformTransactionTransferInvalidToUser() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.TRANSFER);
        User toUser = new User();
        toUser.setUsername("user10");
        transaction.setToUser(toUser);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }

    @Test
    public void testPerformTransactionTransferToUserYourself() {
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user11";
        String password = "password11";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        transaction.setType(TransactionType.TRANSFER);
        User toUser = new User();
        toUser.setUsername(username);
        transaction.setToUser(toUser);
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        ResponseEntity<ErrorResponse> response = template.exchange(url, HttpMethod.POST, entity, ErrorResponse.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Banking Error");
    }

    @Test
    public void testGetTransactionsInvalidUser() {
        System.out.println("testGetTransactionsInvalidUser()");
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user13";
        String password = "password13";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response = template.exchange(url, HttpMethod.GET, entity, Object.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void testGetTransactionsValidUser1() {
        System.out.println("testGetTransactionsValidUser1()");
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user14";
        String password = "password14";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().get("totalTransactions")).isEqualTo(3);
        assertThat(response.getBody().get("pageSize")).isEqualTo(10);
        assertThat(response.getBody().get("totalPages")).isEqualTo(1);
        assertThat(response.getBody().get("pageNumber")).isEqualTo(0);
        assertThat(response.getBody().get("numberOfTransactions")).isEqualTo(3);
    }

    @Test
    public void testGetTransactionsValidUser1PageSize2() {
        System.out.println("testGetTransactionsValidUser1PageSize2()");
        String url = "http://localhost:" + PORT + "/v1/customers/transactions?pageSize=2";
        System.out.println("URL: " + url);
        String username = "user14";
        String password = "password14";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().get("totalTransactions")).isEqualTo(3);
        assertThat(response.getBody().get("pageSize")).isEqualTo(2);
        assertThat(response.getBody().get("totalPages")).isEqualTo(2);
        assertThat(response.getBody().get("pageNumber")).isEqualTo(0);
        assertThat(response.getBody().get("numberOfTransactions")).isEqualTo(2);
    }

    @Test
    public void testGetTransactionsValidUser1PageSize2PageNumber1() {
        System.out.println("testGetTransactionsValidUser1PageSize2PageNumber1()");
        String url = "http://localhost:" + PORT + "/v1/customers/transactions?pageSize=2&pageNumber=1";
        System.out.println("URL: " + url);
        String username = "user14";
        String password = "password14";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().get("totalTransactions")).isEqualTo(3);
        assertThat(response.getBody().get("pageSize")).isEqualTo(2);
        assertThat(response.getBody().get("totalPages")).isEqualTo(2);
        assertThat(response.getBody().get("pageNumber")).isEqualTo(1);
        assertThat(response.getBody().get("numberOfTransactions")).isEqualTo(1);
    }

    @Test
    public void testGetTransactionsValidUser2() {
        System.out.println("testGetTransactionsValidUser2()");
        String url = "http://localhost:" + PORT + "/v1/customers/transactions";
        System.out.println("URL: " + url);
        String username = "user15";
        String password = "password15";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
        System.out.println("Response Status: " + response.getStatusCodeValue());
        System.out.println("Response Body: " + response.getBody());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().get("totalTransactions")).isEqualTo(1);
        assertThat(response.getBody().get("pageSize")).isEqualTo(10);
        assertThat(response.getBody().get("totalPages")).isEqualTo(1);
        assertThat(response.getBody().get("pageNumber")).isEqualTo(0);
        assertThat(response.getBody().get("numberOfTransactions")).isEqualTo(1);
    }
}
