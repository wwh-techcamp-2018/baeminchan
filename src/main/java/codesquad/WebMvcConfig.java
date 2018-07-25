package codesquad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebMvcConfig {
    private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("WebMvcConfig passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }
}
