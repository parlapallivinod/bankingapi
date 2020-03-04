/**
 * @author Vinod Parlapalli
 * created on 2019/12/06
 *
 */

package in.rgukt.r081247.bankingapi.service;


import in.rgukt.r081247.bankingapi.model.User;

public interface UserService {
    User registerUser(User user);
    public User getUser();
    public User updateUserPassword(User user);
    public User deleteUser();
}
