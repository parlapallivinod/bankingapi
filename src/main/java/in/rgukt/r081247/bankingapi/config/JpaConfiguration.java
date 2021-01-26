/**
 * @author Vinod Parlapalli
 * created on 2019/09/09
 * This class contains Jpa related configuration.
 */

package in.rgukt.r081247.bankingapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "in.rgukt.r081247.bankingapi.repository")
public class JpaConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("banking.datasource")
    public DataSourceProperties bankingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("banking.datasource.hikari")
    public HikariDataSource bankingDataSource() {
        return bankingDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("user.datasource")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("user.datasource.hikari")
    public HikariDataSource userDataSource() {
        return userDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
