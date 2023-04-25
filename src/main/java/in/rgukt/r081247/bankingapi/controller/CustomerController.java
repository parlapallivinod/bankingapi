/**
 * @author Vinod Parlapalli
 * Created on 2019/10/14
 *
 */
package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.model.Role;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/v1/customers")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private UserService userService;

	/**
	 * Customer is registered after successful validation.
	 * @param customer: customer object
	 * @return: customer object
	 */
	@PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerCustomer(@Valid @RequestBody User customer) {
		customer = userService.registerUser(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}

	/**
	 * Returns logged in customer
	 * @return: customer object
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomer() {
		User customer = userService.getUser();
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * A customer object with updated password is passed.
	 * Customer is saved and returned after successful password validation.
	 * @param customer: password updated customer
	 * @return: customer object
	 */
	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCustomerPassword(@Valid @RequestBody User customer) {
		customer = userService.updateUserPassword(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * Logged in customer is deleted from the system after successful validation.
	 */
	@DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteCustomer() {
		User user = userService.deleteUser();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
