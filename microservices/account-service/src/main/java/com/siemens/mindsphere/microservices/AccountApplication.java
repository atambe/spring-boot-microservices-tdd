package com.siemens.mindsphere.microservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import com.siemens.mindsphere.microservices.domain.Account;

@RibbonClient(name="accounts-service")
@EnableDiscoveryClient
@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SpringApplication.run(AccountApplication.class, args);
	}

	
	@Bean
	public List<Account> createDataBase(){
		List<Account> accounts = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountNumber("123456789");
		account.setCustomerName("John funk");
		account.setBalance(200.00);
		accounts.add(account);
		return accounts;
	}
	
}
