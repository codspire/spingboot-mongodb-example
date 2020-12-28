package com.codspire.mongodbexample.repository;

import com.codspire.mongodbexample.model.Speaker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpeakerRepository extends MongoRepository<Speaker, String> {
}
