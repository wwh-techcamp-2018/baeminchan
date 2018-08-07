package codesquad;

import codesquad.interceptor.AdminInterceptor;
import codesquad.interceptor.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/users/login").setViewName("login");
        registry.addViewController("/users/join").setViewName("join");
    }



    @Configuration
    @Profile({"prod"})
    public class ProductWebMvcConfig extends WebMvcConfig {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(adminInterceptor())
                    .addPathPatterns("/admin/**").order(1);
        }
    }

    @Configuration
    @Profile({"dev", "local"})
    public class TestWebMvcConfig extends WebMvcConfig {
        @Bean
        public BasicAuthInterceptor basicAuthInterceptor() {
            return new BasicAuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(basicAuthInterceptor()).order(0);
            registry.addInterceptor(adminInterceptor())
                    .addPathPatterns("/admin/**").order(1);
        }
    }

}




