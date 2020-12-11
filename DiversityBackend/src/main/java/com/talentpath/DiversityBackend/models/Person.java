package com.talentpath.DiversityBackend.models;

public class Person {
    private Integer id;
    private String state;
    private String city;
    private Integer age;
    private String gender;
    private String ethnicity;
    private String party;
    private String position;
    private Integer startYear;
    private Integer endYear;

    public Person() {
    }

    public Person(String stateName, String cityName, Integer age, String gender, String ethnicity, String party, String position, Integer startYear, Integer endYear) {
        this.state = stateName;
        this.city = cityName;
        this.age = age;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.party = party;
        this.position = position;
        this.startYear = startYear;
        this.endYear = endYear;
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
