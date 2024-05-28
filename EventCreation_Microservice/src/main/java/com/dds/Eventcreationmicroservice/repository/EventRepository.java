package com.dds.Eventcreationmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dds.Eventcreationmicroservice.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
}
