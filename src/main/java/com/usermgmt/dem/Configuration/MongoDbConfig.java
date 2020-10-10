//package com.usermgmt.dem.Configuration;
//
//import com.usermgmt.dem.domain.User;
//import com.usermgmt.dem.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//public class MongoDbConfig {
//
//    @EnableMongoRepositories(basePackageClasses = UserRepository.class)
//    @Configuration
//    public static  class MongoDBConfig {
//
//
//        @Bean
//        CommandLineRunner commandLineRunner(UserRepository userRepository) {
//            return strings -> {
//
//
//            };
//        }
//
//    }
//
//}
