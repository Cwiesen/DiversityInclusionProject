package com.talentpath.DiversityBackend.controllers;

import com.talentpath.DiversityBackend.services.BackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    BackendService service;

    @PostMapping("/begin")
    public Integer beginGame() {
        return service.addCity();
    }


}
