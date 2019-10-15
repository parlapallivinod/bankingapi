/**
 * @author Vinod Parlapalli
 * created on 2019/09/09
 *
 */
package in.rgukt.r081247.bankingapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "n.rgukt.r081247.bankingapi.repository")
public class JpaConfiguration {
}
