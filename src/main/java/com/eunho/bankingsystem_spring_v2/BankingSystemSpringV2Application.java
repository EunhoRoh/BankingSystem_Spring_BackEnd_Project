package com.eunho.bankingsystem_spring_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing

@SpringBootApplication(scanBasePackages = {"com.eunho.bankingsystem_spring_v2.mapper"})
public class BankingSystemSpringV2Application {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemSpringV2Application.class, args);
    }

}
