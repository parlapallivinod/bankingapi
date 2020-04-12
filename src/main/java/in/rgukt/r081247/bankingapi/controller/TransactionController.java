/**
 * @author Vinod Parlapalli
 * @since 2019-XX-XX
 */

package in.rgukt.r081247.bankingapi.controller;

import in.rgukt.r081247.bankingapi.model.Transaction;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.model.type.TransactionType;
import in.rgukt.r081247.bankingapi.repository.UserRepository;
import in.rgukt.r081247.bankingapi.service.TransactionService;
import in.rgukt.r081247.bankingapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers/transactions")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	/*
	@Autowired
	private UserRepository userRepository;
	 */

	@Autowired
	private TransactionService transactionService;


	/*
	@GetMapping(value="", produces = MediaType.TEXT_HTML_VALUE)
	public String home() {
		LOGGER.info("In TransactionController.home()");
		return "<h2>Banking REST API</h2><hr/>" +
                "TransactionController.home()";
	}
	*/

	/*
	@GetMapping(value = "/post", produces = MediaType.TEXT_HTML_VALUE)
	public String saveTransaction() {
    */
		/*
		Transaction transaction = new Transaction();
		transaction.setType(TransactionType.DEPOSIT);
		transaction.setAmount(100L);

		Optional<User> toUser = userRepository.findById("sehwag");
		if (! toUser.isEmpty())
			transaction.setToUser(toUser.get());
		LOGGER.info("Transaction: " + transaction);
		transactionService.addTransaction(transaction);
		*/

		/*

		Transaction transaction = new Transaction();
		transaction.setType(TransactionType.WITHDRAW);
		transaction.setAmount(100L);
		Optional<User> toUser = userRepository.findById("sehwag");
		if (! toUser.isEmpty())
			transaction.setFromUser(toUser.get());

		transactionService.addTransaction(transaction);

		*/

		/*
		Transaction transaction = new Transaction();
		transaction.setType(TransactionType.TRANSFER);
		transaction.setAmount(100L);
		Optional<User> toUser = userRepository.findById("sehwag");
		if (! toUser.isEmpty())
			transaction.setFromUser(toUser.get());

		Optional<User> fromUser = userRepository.findById("sehwag");
		if (! toUser.isEmpty())
			transaction.setFromUser(toUser.get());

		transactionService.addTransaction(transaction);
		*/

		/*
		Transaction transaction = new Transaction();
		transaction.setType(TransactionType.TRANSFER);
		transaction.setAmount(100L);

		Optional<User> fromUser = userRepository.findById("hariharan");
		if (! fromUser.isEmpty())
			transaction.setFromUser(fromUser.get());

		Optional<User> toUser = userRepository.findById("sehwag");
		if (! toUser.isEmpty())
			transaction.setToUser(toUser.get());


		LOGGER.info("Transaction: " + transaction);
		transactionService.performTransaction(transaction);


		return "<h2>Banking REST API</h2><hr/>" +
				"TransactionController.saveTransaction()";
		*/
		/*
	}
	*/

	/**
	 * Transaction is created and returned after successful validation.
 	 * @param transaction: transaction object
	 * @return: transaction object
	 */
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> performTransaction(@Valid @RequestBody Transaction transaction) {
		LOGGER.info("Transaction: " + transaction);
		Transaction performedTransaction = transactionService.performTransaction(transaction);
		return new ResponseEntity<>(performedTransaction, HttpStatus.CREATED);
	}

	/**
	 * Logged in user's transactions, which are ordered descending by id,
	 * from passed in page number and page size, are returned.
	 * @param pageNumber: page number
	 * @param pageSize: page size
	 * @return: transactions
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTransactions(@RequestParam(defaultValue = "0") Integer pageNumber,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Map<String, Object> map = transactionService.getTransactions(page);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }


}
