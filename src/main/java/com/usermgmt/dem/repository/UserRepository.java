package com.usermgmt.dem.repository;

import com.usermgmt.dem.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String s);

    @Override
    <S extends User> S save(S s);

    User findByEmail(String email);


}
