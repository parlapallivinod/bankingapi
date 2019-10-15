package in.rgukt.r081247.bankingapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers/{name}/accounts/{name}/transactions")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@GetMapping(value="", produces = MediaType.TEXT_HTML_VALUE)
	public String home() {
		LOGGER.info("In TransactionController.home()");
		return "<h2>Banking REST API</h2><hr/>" +
                "TransactionController.home()";
	}
}
