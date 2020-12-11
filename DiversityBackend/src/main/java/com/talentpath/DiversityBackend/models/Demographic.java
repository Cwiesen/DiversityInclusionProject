package com.talentpath.DiversityBackend.models;

public class Demographic {

    private Integer id;
    private Integer year;
    private String city;
    private String state;
    private double percentFemale;
    private double percentWhite;
    private double percentAfricanAmerican;
    private double percentAsian;
    private double percentHispanic;
    private double percentNativeAmerican;
    private Integer age0_17;
    private Integer age18_24;
    private Integer age25_44;
    private Integer age45_64;
    private Integer age65Plus;
    private Integer population;

    public Demographic() {
    }

    public Demographic(Integer year, String city, String state, double percentFemale,
                       double percentWhite, double percentAfricanAmerican, double percentAsian,
                       double percentHispanic, double percentNativeAmerican, Integer age0_17, Integer age18_24,
                       Integer age25_44, Integer age45_64, Integer age65Plus, Integer population) {
        this.year = year;
        this.city = city;
        this.state = state;
        this.percentFemale = percentFemale;
        this.percentWhite = percentWhite;
        this.percentAfricanAmerican = percentAfricanAmerican;
        this.percentAsian = percentAsian;
        this.percentHispanic = percentHispanic;
        this.percentNativeAmerican = percentNativeAmerican;
        this.age0_17 = age0_17;
        this.age18_24 = age18_24;
        this.age25_44 = age25_44;
        this.age45_64 = age45_64;
        this.age65Plus = age65Plus;
        this.population = population;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPercentFemale() {
        return percentFemale;
    }

    public void setPercentFemale(double percentFemale) {
        this.percentFemale = percentFemale;
    }

    public double getPercentWhite() {
        return percentWhite;
    }

    public void setPercentWhite(double percentWhite) {
        this.percentWhite = percentWhite;
    }

    public double getPercentAfricanAmerican() {
        return percentAfricanAmerican;
    }

    public void setPercentAfricanAmerican(double percentAfricanAmerican) {
        this.percentAfricanAmerican = percentAfricanAmerican;
    }

    public double getPercentAsian() {
        return percentAsian;
    }

    public void setPercentAsian(double percentAsian) {
        this.percentAsian = percentAsian;
    }

    public double getPercentHispanic() {
        return percentHispanic;
    }

    public void setPercentHispanic(double percentHispanic) {
        this.percentHispanic = percentHispanic;
    }

    public double getPercentNativeAmerican() {
        return percentNativeAmerican;
    }

    public void setPercentNativeAmerican(double percentNativeAmerican) {
        this.percentNativeAmerican = percentNativeAmerican;
    }

    public Integer getAge0_17() {
        return age0_17;
    }

    public void setAge0_17(Integer age0_17) {
        this.age0_17 = age0_17;
    }

    public Integer getAge18_24() {
        return age18_24;
    }

    public void setAge18_24(Integer age18_24) {
        this.age18_24 = age18_24;
    }

    public Integer getAge25_44() {
        return age25_44;
    }

    public void setAge25_44(Integer age25_44) {
        this.age25_44 = age25_44;
    }

    public Integer getAge45_64() {
        return age45_64;
    }

    public void setAge45_64(Integer age45_64) {
        this.age45_64 = age45_64;
    }

    public Integer getAge65Plus() {
        return age65Plus;
    }

    public void setAge65Plus(Integer age65Plus) {
        this.age65Plus = age65Plus;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
