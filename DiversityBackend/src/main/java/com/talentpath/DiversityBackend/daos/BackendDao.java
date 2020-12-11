package com.talentpath.DiversityBackend.daos;

import com.talentpath.DiversityBackend.models.Demographic;
import com.talentpath.DiversityBackend.models.Person;

import java.util.List;

public interface BackendDao {
    Integer addPerson(Person person);
    Integer addDemographic(Demographic demographic);
    List<Person> getAllPeople();
    List<Demographic> getAllDemographics();
    List<Person> getPeopleByYear(Integer year);
    List<Person> getPeopleByRole(String role);
    List<Person> getPeopleByRoleAndYear(String role, Integer year);
    List<Person> getPeopleByRoleYearAndCity(String role, String year, String city, String state);
    List<Person> getPeopleByCity(String city, String state);
    List<Person> getPeopleByState(String state);
    List<Person> getPeopleByStateAndYear(String state, String year);
}
