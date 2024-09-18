package com.watchingad.watchingad.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.watchingad.watchingad")
@MapperScan(value={"com.watchingad.watchingad.**.mapper"})
@EnableJpaRepositories(basePackages = {"com.watchingad.watchingad.**.repository"})
@EntityScan(basePackages = {"com.watchingad.watchingad.**.entity"})
@EnableScheduling
public class WatchingAdApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchingAdApplication.class, args);
	}

}
