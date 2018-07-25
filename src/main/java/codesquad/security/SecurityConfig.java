package codesquad.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("SecurityConfig passwordEncoder");
        return new BCryptPasswordEncoder();
    }
}
