/**
 * @author Vinod Parlapalli
 * created on 2019/09/09
 * This class contains security configuration for rest web services.
 */

package in.rgukt.r081247.bankingapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("userpass")).roles("CUSTOMER").and()
                .withUser("vinod").password(passwordEncoder().encode("vinodpass")).roles("BANKER");

         */

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

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
                //.antMatchers("/v1/bankers/registration").permitAll()
                .antMatchers("/v1/customers/registration").permitAll()
                .antMatchers("/v1/customers/**").authenticated()
                //.antMatchers("/v1/bankers/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .httpBasic();
        /*
        httpSecurity.authorizeRequests()
                .antMatchers("/banker").hasRole("BANKER")
                .antMatchers("/customer").hasAnyRole("CUSTOMER")
                .antMatchers("/").permitAll()
                .and().httpBasic();
         */
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
