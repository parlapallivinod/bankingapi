package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.model.CustomHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class HomeController {

    @GetMapping("/customHeaderValidation")
    public ResponseEntity<Object> validateCustomHeader(@RequestHeader CustomHeader customHeader) {
        return new ResponseEntity<>(customHeader, HttpStatus.OK);
    }
}
