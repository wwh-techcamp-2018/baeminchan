package codesquad;

import codesquad.interceptor.AdminInterceptor;
import codesquad.interceptor.BasicAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BasicAuthInterceptor basicAuthInterceptor() { return new BasicAuthInterceptor(); }

    @Bean
    public AdminInterceptor adminInterceptor() { return new AdminInterceptor(); }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor()).order(0);
        registry.addInterceptor(adminInterceptor())
                .addPathPatterns("/admin/**").order(1);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/users/login").setViewName("login");
    }
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
//        registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
//    }

//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(30);
//        return messageSource;
//    }

//    @Bean
//    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
//        return new MessageSourceAccessor(messageSource);
//    }

//    @Bean
//    public BasicAuthInterceptor basicAuthInterceptor() {
//        return new BasicAuthInterceptor();
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(basicAuthInterceptor());
//    }

//    @Bean
//    public LoginUserHandlerMethodArgumentResolver loginUserArgumentResolver() {
//        return new LoginUserHandlerMethodArgumentResolver();
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(loginUserArgumentResolver());
//    }
}
