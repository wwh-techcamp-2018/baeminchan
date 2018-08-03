package codesquad.configuration;

import codesquad.security.AdminAuthInterceptor;
import codesquad.security.BasicAuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
public class BaeminchanConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(BaeminchanConfig.class);

    @Value("${spring.cache.ehcache.config}") String ecacheConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(30);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthInterceptor()).addPathPatterns("/admin/**").order(1);

    }

    @Bean
    public AdminAuthInterceptor adminAuthInterceptor() {
        return new AdminAuthInterceptor();
    }

    @Bean
    public CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setShared(true);
        factory.setConfigLocation(new ClassPathResource(ecacheConfig));
        return factory;
    }

    @Configuration
    @Profile("dev")
    public class TestConfig extends BaeminchanConfig {

        @Bean
        public BasicAuthInterceptor basicAuthInterceptor() {
            return new BasicAuthInterceptor();
        }


        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            super.addInterceptors(registry);
            registry.addInterceptor(basicAuthInterceptor()).order(0);
        }
    }
}
