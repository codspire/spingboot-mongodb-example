package com.codspire.mongodbexample.services;

import com.codspire.mongodbexample.model.Speaker;
import com.codspire.mongodbexample.model.SpeakerCriteria;
import com.codspire.mongodbexample.model.SpeakerRepository;
import org.bson.types.ObjectId;
import org.immutables.criteria.backend.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private SpeakerRepository speakerRepository;

    public List<Speaker> findAll() {
        return speakerRepository.findAll().fetch();
    }

    public Speaker findOne(String id) {
        return speakerRepository.find(SpeakerCriteria.speaker.speakerId.is(new ObjectId(id))).fetch().get(0);
    }

    public Speaker save(Speaker speaker) {
        WriteResult result = speakerRepository.insert(speaker);
        LOG.debug("result={}", result);
        return speaker;
    }
}
