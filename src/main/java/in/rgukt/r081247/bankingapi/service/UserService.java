/**
 * @author Vinod Parlapalli
 * created on 2019/11/01
 *
 */

package in.rgukt.r081247.bankingapi.service;

import in.rgukt.r081247.bankingapi.exception.UsernamePresentException;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        LOGGER.info("Begin: UserService.registerUser()");
        LOGGER.info("User: " + user);
        User banker = userRepository.findOneByUsername(user.getUsername());
        if (banker != null) {
            throw new UsernamePresentException("A banker with same username already present. Kindly try with a different username.");
        }
        user = userRepository.save(user);
        LOGGER.info("User: " + user + " has been registered");
        LOGGER.info("End: UserService.registerUser()");
        return user;
    }


}
