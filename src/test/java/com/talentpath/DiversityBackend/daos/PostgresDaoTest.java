package com.talentpath.DiversityBackend.daos;

import com.talentpath.DiversityBackend.models.Demographic;
import com.talentpath.DiversityBackend.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("production")
class PostgresDaoTest {

    @Autowired
    BackendDao dao;

    @Test
    void addPerson() {
        Person person = new Person("Boston","Masechussets",
                26,"M","Democratic","leader",1,2);
        dao.addPerson(person);
    }

    @Test
    void addDemographic() {
        Demographic demo = new Demographic(1,"New-York-City",
                "New York",0,0,0,
                0,0,0,
                0,0,0,0,0,10);
        dao.addDemographic(demo);
    }

    @Test
    void getAllPeople() {
        List<Person> people = dao.getAllPeople();
        assertEquals(2,people.size());
    }

    @Test
    void getAllDemographics() {
        List<Demographic> demographics = dao.getAllDemographics();
        assertEquals(4,demographics.size());
    }

    @Test
    void getPeopleByYear() {
        List<Person> people = dao.getPeopleByYear(1996);
        assertEquals(1,people.size());
    }

    @Test
    void getPersonByRole() {
        List<Person> people = dao.getPeopleByRole("Senator");
        assertEquals(1,people.size());
    }


}