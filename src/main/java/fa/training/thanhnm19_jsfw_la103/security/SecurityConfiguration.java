package fa.training.thanhnm19_jsfw_la103.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
                .formLogin()
                .loginPage("/login") // Define login url
                .loginProcessingUrl("/authentication")
                .successHandler(authenticationSuccessHandler)
                //fixme
                .usernameParameter("email") // default username parameter = "username"
                .permitAll() // public page
        .and()
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
                .authorizeRequests()
        .and()
                .authorizeRequests()
                .antMatchers("/register").permitAll()

                .anyRequest()
                .authenticated();

        return httpSecurity.build();
    }
}
