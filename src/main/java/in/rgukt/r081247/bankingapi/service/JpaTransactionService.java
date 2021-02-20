/**
 * @author Vinod Parlapalli
 * created on 2019/11/01
 *
 */

package in.rgukt.r081247.bankingapi.service;

import in.rgukt.r081247.bankingapi.exception.BankingException;
import in.rgukt.r081247.bankingapi.model.Transaction;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.model.TransactionStatus;
import in.rgukt.r081247.bankingapi.model.TransactionType;
import in.rgukt.r081247.bankingapi.repository.TransactionRepository;
import in.rgukt.r081247.bankingapi.repository.UserRepository;
import in.rgukt.r081247.bankingapi.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class JpaTransactionService implements TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaTransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Secured({"ROLE_CUSTOMER"})
    public Transaction performTransaction(Transaction transaction) {
        LOGGER.info("Transaction: " + transaction);
        transaction.setId(null);
        transaction.setMessage(null);
        String username = UserUtils.getAuthenticatedUsername();
        LOGGER.info("username: " + username);
        User user = userRepository.findById(username).get();
        LOGGER.info("user: " + user);

        switch (transaction.getType()) {
            case DEPOSIT:
                if (transaction.getFromUser() != null) {
                    transaction.setFromUser(null);
                }
                break;
            case TRANSFER:
                if (transaction.getFromUser() != null) {
                    transaction.setFromUser(null);
                }
                break;
            case WITHDRAW:
                if (transaction.getFromUser() != null) {
                    transaction.setFromUser(null);
                }
                if (transaction.getToUser() != null) {
                    transaction.setToUser(null);
                }
                break;
        }

        Optional<User> optionalToUser = Optional.empty();
        LOGGER.info("transaction.getToUser(): " + transaction.getToUser());
        if (transaction.getToUser() != null)
            optionalToUser = userRepository.findById(transaction.getToUser().getUsername());
        User toUser = null;
        if (!optionalToUser.isEmpty()) {
            toUser = optionalToUser.get();
        }
        LOGGER.info("toUser: " + toUser);

        if (transaction.getType() == TransactionType.DEPOSIT
                && transaction.getToUser() != null
                && transaction.getToUser().getUsername() != null
                && toUser == null){
            throw new BankingException("toUser must be a valid customer for a DEPOSIT transaction" +
                    " or Don't provide any toUser to make yourself as toUser");
        }
        if (transaction.getType() == TransactionType.TRANSFER && toUser == null) {
            throw new BankingException("toUser must be a valid customer for a TRANSFER transaction");
        }
        if (transaction.getType() == TransactionType.TRANSFER && user.getUsername().equals(toUser.getUsername())) {
            throw new BankingException("toUser must be a different customer other than you for a TRANSFER transaction");
        }

        switch (transaction.getType()) {
            case DEPOSIT:
                if (toUser == null) {
                    transaction.setToUser(user);
                } else {
                    transaction.setToUser(toUser);
                }
                break;
            case TRANSFER:
                transaction.setFromUser(user);
                transaction.setToUser(toUser);
                break;
            case WITHDRAW:
                transaction.setFromUser(user);
                break;
        }

        LOGGER.info("Transaction: " + transaction);
        User fromUser = transaction.getFromUser();
        toUser = transaction.getToUser();
        switch (transaction.getType()) {
            case DEPOSIT:
                toUser.setBalance(toUser.getBalance() + transaction.getAmount());
                transaction.setStatus(TransactionStatus.SUCCESS);
                break;
            case TRANSFER:
                if (fromUser.getBalance() < transaction.getAmount()) {
                    transaction.setStatus(TransactionStatus.FAILURE);
                    transaction.setMessage("Transfer Failure: Balance < Amount");
                } else {
                    fromUser.setBalance(fromUser.getBalance() - transaction.getAmount());
                    toUser.setBalance(toUser.getBalance() + transaction.getAmount());
                    transaction.setStatus(TransactionStatus.SUCCESS);
                }
                break;
            case WITHDRAW:
                if (fromUser.getBalance() < transaction.getAmount()) {
                    transaction.setStatus(TransactionStatus.FAILURE);
                    transaction.setMessage("Withdraw Failure: Balance < Amount");
                } else {
                    fromUser.setBalance(fromUser.getBalance() - transaction.getAmount());
                    transaction.setStatus(TransactionStatus.SUCCESS);
                }
                break;
        }

        transaction.setCreatedTime(LocalDateTime.now());
        LOGGER.info("toUser: " + toUser);
        LOGGER.info("fromUser: " + fromUser);
        Transaction savedTransaction = transactionRepository.save(transaction);
        LOGGER.info("Transaction saved " + savedTransaction);
        return savedTransaction;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured({"ROLE_CUSTOMER"})
    public Map<String, Object> getTransactions(Pageable pageable) {
        String username = UserUtils.getAuthenticatedUsername();
        LOGGER.info("Username: " + username);
        User user = userRepository.findById(username).get();
        LOGGER.info("user: " + user);

        LOGGER.info("Pageable: " + pageable);
        Page<Transaction> page = transactionRepository.findByFromUserOrToUser(user, user, pageable);
        Map<String, Object> transactionsMap = new HashMap<>();


        transactionsMap.put("totalTransactions", page.getTotalElements());
        transactionsMap.put("pageSize", page.getSize());
        transactionsMap.put("totalPages", page.getTotalPages());
        transactionsMap.put("pageNumber", page.getNumber());
        transactionsMap.put("numberOfTransactions", page.getNumberOfElements());
        transactionsMap.put("transactions", page.getContent());
        LOGGER.info("transactionsMap: " + transactionsMap);
        return transactionsMap;
    }
}
