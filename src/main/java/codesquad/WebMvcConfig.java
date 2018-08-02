package codesquad;

import codesquad.security.AdminAuthorizationInterceptor;
import codesquad.security.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public abstract class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public abstract PasswordEncoder passwordEncoder();

    @Bean
    public AdminAuthorizationInterceptor adminAuthorizationInterceptor() {return new AdminAuthorizationInterceptor(); }


    @Configuration
    @Profile("test")
    static class TestWebMvcConfig extends  WebMvcConfig {
        @Bean
        public PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(loginInterceptor()).order(1);
            registry.addInterceptor(adminAuthorizationInterceptor()).order(2).addPathPatterns("/api/admin");
        }
        @Bean
        public LoginInterceptor loginInterceptor(){
            return new LoginInterceptor();
        }

    }

    @Configuration
    @Profile({"prod", "dev","local"})
    static class NotTestWebMvcConfig extends WebMvcConfig{
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(adminAuthorizationInterceptor()).addPathPatterns("/api/admin");
        }
    }
}
