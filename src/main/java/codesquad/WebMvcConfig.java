package codesquad;

import codesquad.security.AdminRoleInterceptor;
import codesquad.security.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public BasicAuthInterceptor basicAuthInterceptor() {
        return new BasicAuthInterceptor();
    }

    @Bean
    public AdminRoleInterceptor adminRoleInterceptor() { return new AdminRoleInterceptor(); }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor()).order(Ordered.HIGHEST_PRECEDENCE);
        registry.addInterceptor(adminRoleInterceptor()).addPathPatterns("/api/admin/**").order(Ordered.LOWEST_PRECEDENCE);
    }
}
