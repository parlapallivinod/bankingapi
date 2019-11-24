/**
 * @author Vinod Parlapalli
 * created on 2019/11/01
 *
 */

package in.rgukt.r081247.bankingapi.service;

import in.rgukt.r081247.bankingapi.exception.UserNotPresentException;
import in.rgukt.r081247.bankingapi.exception.UserPresentException;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.repository.UserRepository;
import in.rgukt.r081247.bankingapi.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        LOGGER.info("User: " + user);
        Optional<User> banker = userRepository.findById(user.getUsername());
        if (banker.isPresent()) {
            throw new UserPresentException("A user with '" + user.getUsername() + "' username already present in the system. Kindly try with a different username.");
        }
        user = userRepository.save(user);
        LOGGER.info("User has been registered");
        return user;
    }

    @Secured({"ROLE_CUSTOMER"})
    public User updateUserPassword(User user) {
        LOGGER.info("User from request: " + user);
        User userFromRepository = userRepository.findById(UserUtil.getAuthenticatedUsername()).get();
        LOGGER.info("User from repository: " + userFromRepository);
        userFromRepository.setPassword(user.getPassword());
        userFromRepository.setLastUpdatedTime(LocalDateTime.now());
        User passwordUpdatedUser = userRepository.save(userFromRepository);
        LOGGER.info("User after password updation: " + passwordUpdatedUser);
        return passwordUpdatedUser;

    }


}
