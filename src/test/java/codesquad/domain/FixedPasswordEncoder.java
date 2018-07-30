package codesquad.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FixedPasswordEncoder implements PasswordEncoder {
    
     private static final Logger log = LoggerFactory.getLogger(FixedPasswordEncoder.class);
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.debug("raw password : {}", rawPassword);
        log.debug("encoded password : {}", encodedPassword);

        return rawPassword.toString().equals(encodedPassword);
    }
}
