package codesquad.config;

import codesquad.security.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("test")
public class TestMvcConfig implements WebMvcConfigurer {
    @Bean
    public BasicAuthInterceptor basicAuthInterceptor() {
        return new BasicAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor())
                .addPathPatterns("/api/promotions/**")
                .addPathPatterns("/api/admin/**")
                .order(Ordered.HIGHEST_PRECEDENCE);
    }
}
