package com.talentpath.DiversityBackend.daos;

import com.talentpath.DiversityBackend.models.Demographic;
import com.talentpath.DiversityBackend.models.Person;

import java.util.List;

public interface BackendDao {
    Integer addPerson(Person person);
    Integer addDemographic(Demographic demographic);
    List<Person> getAllPeople();
    List<Demographic> getAllDemographics();
}
