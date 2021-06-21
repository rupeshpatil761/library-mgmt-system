package com.book.service;

import com.book.service.repository.dynamo.BookRepositoryDynamo;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication()
@EnableJpaAuditing
@EnableJpaRepositories(excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BookRepositoryDynamo.class))
@EnableDynamoDBRepositories("com.book.service.repository.dynamo")
@EnableTransactionManagement
@EnableDiscoveryClient
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
