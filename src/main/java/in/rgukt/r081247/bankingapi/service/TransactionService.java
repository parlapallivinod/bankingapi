/**
 * @author Vinod Parlapalli
 * created on 2019/12/06
 *
 */

package in.rgukt.r081247.bankingapi.service;


import in.rgukt.r081247.bankingapi.model.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TransactionService {
    Transaction performTransaction(Transaction transaction);
    Map<String, Object> getTransactions(Pageable pageable);
}
