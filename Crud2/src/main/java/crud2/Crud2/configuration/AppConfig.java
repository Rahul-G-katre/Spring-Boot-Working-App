package crud2.Crud2.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user1 = User.builder().username("Rahul").password(passwordEncoder().encode("Rahul@123")).roles("user1").build();
        UserDetails user2 = User.builder().username("Yogesh").password(passwordEncoder().encode("Yogesh@123")).roles("user2").build();
        UserDetails user3 = User.builder().username("Admin").password(passwordEncoder().encode("Admin@123")).roles("Admin").build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
            return builder.getAuthenticationManager();


        }



}
