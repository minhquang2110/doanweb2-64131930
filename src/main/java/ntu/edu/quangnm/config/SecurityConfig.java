package ntu.edu.quangnm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import ntu.edu.quangnm.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private CustomUserDetailsService customUserDetailsService;

	private final String[] ALL_PEOPLE= {"/user/**"};
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/scientists/list","/statistics","/scientists/filter","scientists/details/{id}").permitAll()
                .requestMatchers("/admin/**").hasRole("Admin")
                .requestMatchers("/scientist/**").hasRole("Scientist")
                .requestMatchers(ALL_PEOPLE).hasAnyRole("Scientist", "Admin")
                .anyRequest().authenticated()
            )
	            .formLogin(form -> form
	                .loginPage("/loginTemplate") 
	                .loginProcessingUrl("/login") 
	                .defaultSuccessUrl("/", true)
	                .failureUrl("/user/loginTemplateFailed")
	                .permitAll()
	            )
            .logout(logout -> logout.permitAll())
            .userDetailsService(customUserDetailsService)
            .build();
    }

}