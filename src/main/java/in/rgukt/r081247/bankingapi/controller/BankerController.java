/**
 * @author Vinod Parlapalli
 * Created on 2019/10/14
 * A banker can create any number of banks.
 */
package in.rgukt.r081247.bankingapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bankers")
public class BankerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BankerController.class);

	@PostMapping(value="/registration", produces = MediaType.TEXT_HTML_VALUE)
	public String registerBanker() {
		LOGGER.info("In BankerController.registerBanker()");
		return "<h2>Banking REST API</h2><hr/>" +
                "BankerController.registerBanker()";
	}

	@PutMapping(value="/{username}", produces = MediaType.TEXT_HTML_VALUE)
	public String updatePassword() {
		LOGGER.info("In BankerController.updatePassword()");
		return "<h2>Banking REST API</h2><hr/>" +
				"BankerController.updatePassword()";
	}

	@DeleteMapping(value="/{username}", produces = MediaType.TEXT_HTML_VALUE)
	public String deleteBanker() {
		LOGGER.info("In BankerController.deleteBanker()");
		return "<h2>Banking REST API</h2><hr/>" +
				"BankerController.deleteBanker()";
	}
}
