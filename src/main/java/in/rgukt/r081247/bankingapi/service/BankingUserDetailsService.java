/**
 * @author Vinod Parlapalli
 * created on 2019/11/17
 *
 */

package in.rgukt.r081247.bankingapi.service;

import in.rgukt.r081247.bankingapi.model.BankingUserDetails;
import in.rgukt.r081247.bankingapi.model.User;
import in.rgukt.r081247.bankingapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankingUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankingUserDetailsService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("username: '" + username + "'");
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()) {
            LOGGER.info("User with '" + username + "' username not present in the system.");
            throw new UsernameNotFoundException("User with '" + username + "' username not present in the system.");
        }
        LOGGER.info("User: " + user.get());
        return new BankingUserDetails(user.get());
    }
}
