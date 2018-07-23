package codesquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"codesquad"})
public class BaeminchanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaeminchanApplication.class, args);
    }
}
