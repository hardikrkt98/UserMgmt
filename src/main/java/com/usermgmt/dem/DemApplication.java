package com.usermgmt.dem;

import com.usermgmt.dem.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//@ComponentScan({"com.usermgmt.dem.Service","com.usermgmt.dem.repository","com.usermgmt.dem.resource","com.usermgmt.dem.fitler"})

//@EnableMongoRepositories("com.usermgmt.dem.repository")
@SpringBootApplication
public class DemApplication {

		public static void main(String[] args) {


			ApplicationContext applicationContext =
					SpringApplication.run(DemApplication.class, args);

//			for (String name : applicationContext.getBeanDefinitionNames()) {
//				System.out.println(name);
//			}
	}



	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}



}
