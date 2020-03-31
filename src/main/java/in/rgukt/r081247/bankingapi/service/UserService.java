/**
 * @author Vinod Parlapalli
 * created on 2019/12/06
 *
 */

package in.rgukt.r081247.bankingapi.service;


import in.rgukt.r081247.bankingapi.model.User;

public interface UserService {
    User registerUser(User user);
    User getUser();
    User updateUserPassword(User user);
    User deleteUser();
}
