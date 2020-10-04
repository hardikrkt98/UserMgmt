package com.usermgmt.dem.repository;

import com.usermgmt.dem.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {

User findByUsername(String username);

User findByEmail(String email);


}
