package com.talentpath.DiversityBackend.models;

public class Person {
    private Integer id;
    private String state;
    private String city;
    private String name;
    private Integer age;
    private String gender;
    private String ethnicity;
    private String party;
    private String position;
    private Integer startYear;
    private Integer endYear;
    private Integer personId;


    public Person() {
    }

    public Person(Integer id, String stateName, String cityName, String name, Integer age, String gender, String ethnicity, String party, String position, Integer startYear, Integer endYear, Integer personId) {
        this.id = id;
        this.state = stateName;
        this.city = cityName;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.party = party;
        this.position = position;
        this.startYear = startYear;
        this.endYear = endYear;
        this.personId = personId;
    }

    public Person(String stateName, String cityName, Integer age, String gender,
                  String party, String position, Integer startYear, Integer endYear) {
        this.state = stateName;
        this.city = cityName;
        this.age = age;
        this.gender = gender;
        this.party = party;
        this.position = position;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getStateName() {
        return state;
    }

    public void setStateName(String stateName) {
        this.state = stateName;
    }

    public String getCityName() {
        return city;
    }

    public void setCityName(String cityName) {
        this.city = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }
}
