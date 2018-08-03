package codesquad.config;

import codesquad.security.AdminAuthInterceptor;
import codesquad.security.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Configuration
    @Profile({ "test" })
    static class TestWebMvcConfig extends WebMvcConfig {
        @Bean
        public BasicAuthInterceptor basicAuthInterceptor() {
            return new BasicAuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(basicAuthInterceptor()).order(Ordered.HIGHEST_PRECEDENCE);
        }
    }

    @Configuration
    @Profile({ "test", "local", "prod" })
    static class NotTestWebMvcConfig extends WebMvcConfig {
        @Bean
        public AdminAuthInterceptor adminAuthInterceptor() {
            return new AdminAuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(adminAuthInterceptor()).addPathPatterns("/admin/**").order(Ordered.LOWEST_PRECEDENCE);;
        }

    }
}
