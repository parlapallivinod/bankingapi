package in.rgukt.r081247.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BankingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BankingException(String exception) {
        super(exception);
    }
}