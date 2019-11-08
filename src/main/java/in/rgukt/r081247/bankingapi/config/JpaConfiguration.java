/**
 * @author Vinod Parlapalli
 * created on 2019/09/09
 * This class contains Jpa related configuration.
 */

package in.rgukt.r081247.bankingapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "in.rgukt.r081247.bankingapi.repository")
public class JpaConfiguration {
}
