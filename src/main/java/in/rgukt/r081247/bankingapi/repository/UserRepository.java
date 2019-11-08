/**
 * @author Vinod Parlapalli
 * created on 2019/11/01
 *
 */

package in.rgukt.r081247.bankingapi.repository;

import in.rgukt.r081247.bankingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findOneByUsername(String username);
}
