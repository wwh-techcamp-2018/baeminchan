//package codesquad.security;
//
//import codesquad.support.BeanConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//    @Autowired
//    private BeanConfig beanConfig;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ApiAdminInterceptor())
//                .addPathPatterns("/api/admin/**");
//
////        registry.addInterceptor(new AdminInterceptor())
////                .addPathPatterns("/admin*");
//
//
//        registry.addInterceptor(beanConfig.basicAuthInterceptor());
//    }
//}
