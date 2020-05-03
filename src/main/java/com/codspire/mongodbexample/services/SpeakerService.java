package com.codspire.mongodbexample.services;

import com.codspire.mongodbexample.model.Speaker;
import com.codspire.mongodbexample.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public Speaker findOne(String id) {
        return speakerRepository.findById(id)
                .map(s -> s)
                .orElse(null);
    }

    public Speaker save(Speaker speaker) {
        return speakerRepository.save(speaker);
    }
}
