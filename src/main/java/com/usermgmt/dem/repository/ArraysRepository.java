package com.usermgmt.dem.repository;

import com.usermgmt.dem.domain.arrays;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArraysRepository extends MongoRepository<arrays,String> {

arrays findBy_id (String id);

arrays findBytitle(String title);


    @Override
    List<arrays> findAll();
}
