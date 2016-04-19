package com.ennovate;

import com.ennovate.condition.ConditionalOnProfiles;
import com.ennovate.service.ConditionalBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ConditionalApplication {


	@Bean
	@ConditionalOnProfiles(value = {"A", "B"})
	@Primary
	public ConditionalBean conditionalOnAB()
	{
		return new ConditionalBean() {
			@Override
			public String getValue() {
				return "AB";
			}
		};
	}

	@Bean
	@ConditionalOnProfiles
	@Primary
	public ConditionalBean conditionalOnDefault()
	{
		return new ConditionalBean() {
			@Override
			public String getValue() {
				return "Default";
			}
		};
	}

	@Bean
	@Profile(value = {"A"})
	public ConditionalBean conditionalOnA()
	{
		return new ConditionalBean() {
			@Override
			public String getValue() {
				return "A";
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ConditionalApplication.class, args);
	}
}
