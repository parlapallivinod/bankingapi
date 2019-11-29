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
@RequestMapping("/v1/customers")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value="/registration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerCustomer(@Valid @RequestBody User customer) {
		Role role = new Role();
		role.setRolename("ROLE_CUSTOMER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);

		Set<User> users = new HashSet<>();
		users.add(customer);

		role.setUsers(users);
		customer.setRoles(roles);
		customer.setCreatedTime(LocalDateTime.now());

		customer = userService.registerUser(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}

	@PutMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCustomerPassword(@Valid @RequestBody User customer) {
		customer = userService.updateUserPassword(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@DeleteMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteCustomer() {
		User user = userService.deleteUser();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
