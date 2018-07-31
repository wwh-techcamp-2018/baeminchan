package codesquad.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiAdminInterceptor())
                .addPathPatterns("/api/admin/**");

//        registry.addInterceptor(new AdminInterceptor())
//                .addPathPatterns("/admin*");
    }
}
