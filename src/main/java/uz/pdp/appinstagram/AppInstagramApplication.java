package uz.pdp.appinstagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppInstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppInstagramApplication.class, args);
    }

}
