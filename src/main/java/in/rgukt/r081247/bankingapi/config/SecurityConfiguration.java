/**
 * @author Vinod Parlapalli
 * created on 2019/09/09
 *
 */
package in.rgukt.r081247.bankingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password(passwordEncoder().encode("userpass")).roles("USER").and()
            .withUser("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	System.out.println("add test");
        httpSecurity
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	/*
    	 httpSecurity
         .authorizeRequests()
         .anyRequest()
         .fullyAuthenticated()
         //.antMatchers("*rest/*")
         .and()
         //.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
         .httpBasic();
    	 httpSecurity.csrf().disable();
    	 */
        httpSecurity.authorizeRequests()
                .antMatchers("/v1").permitAll()
                .antMatchers("/v1/bankers/registration").permitAll()
                .antMatchers("/v1/customers/registration").permitAll()
                .antMatchers("/v1/customers/**").hasRole("CUSTOMER")
                .antMatchers("/v1/bankers/**").hasRole("BANKER")
                .httpBasic();
    	/*
        httpSecurity.authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
                .antMatchers("/**")
                .permitAll()
                .and()
                .httpBasic();
    	 */
        httpSecurity.csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
