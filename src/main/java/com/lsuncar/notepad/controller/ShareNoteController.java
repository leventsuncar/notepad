package com.lsuncar.notepad.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShareNoteController {

    @Autowired
    private ProducerTemplate producerTemplate;



}
