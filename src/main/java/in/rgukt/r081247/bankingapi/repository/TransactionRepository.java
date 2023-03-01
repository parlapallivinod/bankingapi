/**
 * @author Vinod Parlapalli
 * created on 2019/12/29
 *
 */

package in.rgukt.r081247.bankingapi.repository;

import in.rgukt.r081247.bankingapi.model.Transaction;
import in.rgukt.r081247.bankingapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByFromUserOrToUser(User fromUser, User toUser, Pageable pageable);
}
