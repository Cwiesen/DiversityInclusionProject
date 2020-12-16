package com.talentpath.DiversityBackend.controllers;

import com.talentpath.DiversityBackend.models.Demographic;
import com.talentpath.DiversityBackend.models.Person;
import com.talentpath.DiversityBackend.services.BackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    BackendService service;

    @PostMapping("/begin")
    public Integer beginGame() {

        return 1;
    }

    @GetMapping("/demographics")
    public List<Demographic> getDemographics() {
        return service.getDemographics();
    }

    @GetMapping("/people")
    public List<Person> getPeople() {

        return service.getAllPeople();

    }

    @GetMapping("/readFile")
    public List<Integer> readFile() {
        return service.readFile();
    }

    @PostMapping("/addPersonTest")
    public Integer addPerson() {
        service.addPerson();
        return 2;
    }
}
