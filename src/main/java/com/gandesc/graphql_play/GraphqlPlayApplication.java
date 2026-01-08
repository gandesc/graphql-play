package com.gandesc.graphql_play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.gandesc.graphql_play.${lec}")
@EnableR2dbcRepositories(basePackages = "com.gandesc.graphql_play.${lec}")
public class GraphqlPlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlayApplication.class, args);
	}

}
