package in.rgukt.r081247.bankingapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {

    @GetMapping("")
    public void getSwaggerUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect("swagger-ui/");
    }
}
