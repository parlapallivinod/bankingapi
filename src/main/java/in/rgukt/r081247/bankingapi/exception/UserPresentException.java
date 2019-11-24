/**
 * @author Vinod Parlapalli
 * created on 2019/11/03
 *
 */

package in.rgukt.r081247.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserPresentException(String exception) {
        super(exception);
    }
}
