package io.github.shyamz.conditional;

import io.github.shyamz.conditional.service.ConditionalBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ConditionalApplication {

    @Bean
    @ConditionalOnAllProfiles(value = {"A", "B"})
    @Primary
    public ConditionalBean conditionalOnAB() {
        return () -> "AB";
    }

    @Bean
    @Profile(value = {"A"})
    public ConditionalBean conditionalOnA() {
        return () -> "A";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConditionalApplication.class, args);
    }
}
