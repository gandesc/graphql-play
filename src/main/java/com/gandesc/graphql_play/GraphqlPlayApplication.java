package com.gandesc.graphql_play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gandesc.graphql_play.lec10")
public class GraphqlPlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlayApplication.class, args);
	}

}
