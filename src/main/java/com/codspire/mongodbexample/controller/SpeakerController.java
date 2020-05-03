package com.codspire.mongodbexample.controller;

import com.codspire.mongodbexample.model.Speaker;
import com.codspire.mongodbexample.services.SpeakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/speakers")
public class SpeakerController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Speaker> getAllSpeaker() {
        LOG.info("Getting all Speaker.");
        return speakerService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Speaker getSpeakerById(@PathVariable String id) {
        LOG.info("Getting Speaker with ID: {}.", id);
        return speakerService.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Speaker addNewUsers(@RequestBody Speaker speaker) {
        LOG.info("Saving Speaker.");
        return speakerService.save(speaker);
    }
}
