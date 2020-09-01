/**
 * @author Vinod Parlapalli
 * created on 2019/11/01
 *
 */

package in.rgukt.r081247.bankingapi.service;

import in.rgukt.r081247.bankingapi.exception.BankingException;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.repository.UserRepository;;
import in.rgukt.r081247.bankingapi.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class JpaUserService implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaUserService.class);

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        LOGGER.info("User: " + user);
        Optional<User> banker = userRepository.findById(user.getUsername());
        if (banker.isPresent()) {
            throw new BankingException("A user with '" + user.getUsername() + "' username already present in the system. Kindly try with a different username.");
        }
        user = userRepository.save(user);
        LOGGER.info("User: " + user + " has been registered");
        return user;
    }

    @Transactional(readOnly = true)
    @Secured({"ROLE_CUSTOMER"})
    public User getUser() {
        String username = UserUtils.getAuthenticatedUsername();
        LOGGER.info("Username: " + username);
        Optional<User> optionalUser = userRepository.findById(username);
        if (! optionalUser.isPresent()) {
            throw new BankingException("User with '" + username + "' username not found in the system.");
        }
        User user = optionalUser.get();
        LOGGER.info("User: " + user);
        return user;
    }

    @Secured({"ROLE_CUSTOMER"})
    public User updateUserPassword(User user) {
        LOGGER.info("User from request: " + user);
        String username = UserUtils.getAuthenticatedUsername();
        LOGGER.info("Username: " + username);
        Optional<User> optionalUserFromRepository = userRepository.findById(username);
        if (! optionalUserFromRepository.isPresent()) {
            throw new BankingException("User with '" + username + "' username not found in the system.");
        }
        User userFromRepository = optionalUserFromRepository.get();
        LOGGER.info("User from repository: " + userFromRepository);
        userFromRepository.setPassword(user.getPassword());
        userFromRepository.setLastUpdatedTime(LocalDateTime.now());
        User passwordUpdatedUser = userRepository.save(userFromRepository);
        LOGGER.info("User after password updation: " + passwordUpdatedUser);
        return passwordUpdatedUser;
    }

    @Secured({"ROLE_CUSTOMER"})
    public User deleteUser() {
        String username = UserUtils.getAuthenticatedUsername();
        LOGGER.info("Username: " + username);
        Optional<User> optionalUser = userRepository.findById(username);
        if (! optionalUser.isPresent()) {
            throw new BankingException("User with '" + username + "' username not found in the system.");
        }
        User user = optionalUser.get();
        if (user.getBalance() != 0) {
            throw new BankingException("User, with non zero balance, cannot be deleted.");
        }
        userRepository.delete(user);
        LOGGER.info("User: " + user + " deleted");
        return user;
    }


}
