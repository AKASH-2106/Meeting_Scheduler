package com.dds.Eventcreationmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dds.Eventcreationmicroservice.model.Free;
import java.util.List;


public interface FreeRepository extends MongoRepository<Free, String> {
    List<Free> findByMeetid(String meetid);
}