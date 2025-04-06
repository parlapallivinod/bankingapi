/**
 * @author Vinod Parlapalli
 * created on 2019/09/08
 * This class is the starting point for this application.
 */

package in.rgukt.r081247.bankingapi;

import com.fasterxml.jackson.core.type.TypeReference;
import in.rgukt.r081247.bankingapi.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BankingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApiApplication.class, args);
	}

}
