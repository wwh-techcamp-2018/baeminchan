package codesquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BaeminchanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaeminchanApplication.class, args);
    }
}
