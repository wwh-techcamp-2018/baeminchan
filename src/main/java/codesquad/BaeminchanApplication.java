package codesquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan({"codesquad" })
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class BaeminchanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaeminchanApplication.class, args);
    }

}
