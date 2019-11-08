/**
 * @author Vinod Parlapalli
 * Created on 2019/10/14
 * A banker can create any number of banks.
 */
package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.model.Role;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.repository.UserRepository;
import in.rgukt.r081247.bankingapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/bankers")
public class BankerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BankerController.class);

	/*
	@Autowired
	private UserRepository userRepository;
	*/

	@Autowired
	private UserService userService;

	@PostMapping(value="/registration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerBanker(@Valid @RequestBody User banker) {
		LOGGER.info("Begin: BankerController.registerBanker()");
		Role role = new Role();
		role.setRolename("ROLE_BANKER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);

		Set<User> users = new HashSet<>();
		users.add(banker);

		role.setUsers(users);
		banker.setRoles(roles);
		banker.setCreatedTime(LocalDateTime.now());
		banker = userService.registerUser(banker);
		LOGGER.info("End: BankerController.registerBanker()");
		return new ResponseEntity<>(banker, HttpStatus.CREATED);

		/*
		Role role = new Role();
		role.setRolename("ROLE_BANKER");

		Set<Role> roles = new HashSet<>();
		roles.add(role);

		User user = new User("jaya", "jayapass", LocalDateTime.now(), null, roles);

		Set<User> users = new HashSet<>();
		users.add(user);

		role.setUsers(users);

		LOGGER.info("Before registering user: " + user);
		userRepository.save(user);
		LOGGER.info("After registering user: " + user);

		return "<h2>Banking REST API</h2><hr/>" +
				"BankerController.registerBanker()";
		 */
		/*
		Role role = new Role();
		role.setRolename("ROLE_BANKER");

		Set<Role> roles = new HashSet<>();
		roles.add(role);

		User user = new User("sam", "sampass", LocalDateTime.now(), null, roles);

		Set<User> users = new HashSet<>();
		users.add(user);

		role.setUsers(users);
		userService.registerUser(user);
		 */
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
