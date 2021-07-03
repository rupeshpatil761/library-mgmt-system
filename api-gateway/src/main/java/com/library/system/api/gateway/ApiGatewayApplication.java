package com.library.system.api.gateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
public class ApiGatewayApplication {

	/*
		Add below bean config to avoid below error/exception when we call http://localhost:8765/user-service/users

		io.netty.resolver.dns.DnsResolveContext$SearchDomainUnknownHostException: Search domain query failed.

	 */
	@Bean
	public HttpClient httpClient() {
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
