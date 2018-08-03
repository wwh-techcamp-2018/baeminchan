package codesquad.config;

import codesquad.security.AdminSessionHandlerMethodArgumentResolver;
import codesquad.security.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public abstract class WebConfig implements WebMvcConfigurer {
    @Bean
    public AdminSessionHandlerMethodArgumentResolver adminUserHandlerMethodArgumentResolver() {
        return new AdminSessionHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(adminUserHandlerMethodArgumentResolver());
    }

    @Configuration
    @Profile({"dev"})
    static class TestWebConfig extends WebConfig {
        @Bean
        public BasicAuthInterceptor basicAuthInterceptor() {
            return new BasicAuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(basicAuthInterceptor());
        }

    }

}
