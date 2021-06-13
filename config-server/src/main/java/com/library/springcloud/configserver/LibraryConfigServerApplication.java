package com.library.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class LibraryConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryConfigServerApplication.class, args);
	}

}
