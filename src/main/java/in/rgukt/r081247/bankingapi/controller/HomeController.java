/**
 * @author Vinod Parlapalli
 * @since 2019-XX-XX
 * Home resource having Requirements document link.
 */
package in.rgukt.r081247.bankingapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@GetMapping(value="/v1", produces = MediaType.TEXT_HTML_VALUE)
	public String home() {
		LOGGER.info("In HomeController.home()");
		return "<h2>Banking REST API</h2><hr/>" +
				"<ul>" +
				"<li><a href='./banking_srs.txt'> Requirements Document </a></li>" +
				"</ul>";
	}
}
