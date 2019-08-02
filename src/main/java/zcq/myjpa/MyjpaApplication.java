package zcq.myjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"zcq.myjpa.repository"})
public class MyjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyjpaApplication.class, args);
    }

}
