package codesquad;

import codesquad.security.AdminAuthorizationInterceptor;
import codesquad.security.LoginInterceptor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableCaching
public abstract class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public abstract PasswordEncoder passwordEncoder();

    @Bean
    public AdminAuthorizationInterceptor adminAuthorizationInterceptor() {return new AdminAuthorizationInterceptor(); }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);
        return cmfb;
    }

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
