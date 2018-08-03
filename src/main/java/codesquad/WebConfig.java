package codesquad;

import codesquad.security.AdminInterceptor;
import codesquad.security.ApiAdminInterceptor;
import codesquad.security.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public abstract class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApiAdminInterceptor apiAdminInterceptor() {
        return new ApiAdminInterceptor();
    }

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }

    @Configuration
    @Profile("product")
    static class ProductWebConfig extends WebConfig {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(apiAdminInterceptor())
                    .addPathPatterns("/api/admin/**");

            registry.addInterceptor(adminInterceptor())
                    .addPathPatterns("/admin*");
        }

    }

    @Configuration
    @Profile("develop")
    static class DevWebConfig extends WebConfig {
        @Bean
        public BasicAuthInterceptor basicAuthInterceptor() {
            return new BasicAuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(basicAuthInterceptor());

            registry.addInterceptor(apiAdminInterceptor())
                    .addPathPatterns("/api/admin/**");

            registry.addInterceptor(adminInterceptor())
                    .addPathPatterns("/admin*");
        }

    }
}
