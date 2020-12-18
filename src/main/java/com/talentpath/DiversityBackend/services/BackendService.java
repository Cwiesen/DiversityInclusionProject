package com.talentpath.DiversityBackend.services;

import com.talentpath.DiversityBackend.daos.BackendDao;
import com.talentpath.DiversityBackend.models.Demographic;
import com.talentpath.DiversityBackend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BackendService {

    BackendDao dao;

    @Autowired
    public BackendService(BackendDao dao) {
        this.dao = dao;
    }

    public List<Integer> readGovernor() {
        List<String> fileRows = new ArrayList<>();
        int rowSize = 10;
        try {
            File fileData = new File("Governors.csv");
            Scanner myReader = new Scanner(fileData);
            while (myReader.hasNextLine()) {
                fileRows.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        List<Integer> addedIds = new ArrayList<>();
        for (int i = 1; i < fileRows.size(); i++) {
            String line = fileRows.get(i);
            line.replace("\"", "");
            String[] currentLine = line.split(",");
            if (currentLine.length < rowSize) {
                System.out.println("Bad index");
            }
            addedIds.add(dao.addPerson(buildGovernor(currentLine)));
        }
        return addedIds;
    }

    public List<Integer> readMayor() {
        List<String> fileRows = new ArrayList<>();
        int rowSize = 11;
        try {
            File fileData = new File("Mayors.csv");
            Scanner myReader = new Scanner(fileData);
            while (myReader.hasNextLine()) {
                fileRows.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        List<Integer> addedIds = new ArrayList<>();
        for (int i = 1; i < fileRows.size(); i++) {
            String line = fileRows.get(i);
            line.replace("\"", "");
            String[] currentLine = line.split(",");
            if (currentLine.length < rowSize) {
                System.out.println("Bad index");
            }
            addedIds.add(dao.addPerson(buildMayor(currentLine)));
        }
        return addedIds;
    }

    public List<Integer> readCongress() {
        List<String> conTermInfo = new ArrayList<>();
        List<String> conEthInfo = new ArrayList<>();
        try {
            File fileData = new File("House_Sen.csv");
            Scanner myReader = new Scanner(fileData);
            while (myReader.hasNextLine()) {
                conTermInfo.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File fileData = new File("House_Sen_Eth.csv");
            Scanner myReader = new Scanner(fileData);
            while (myReader.hasNextLine()) {
                conEthInfo.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if(conTermInfo.size() != conEthInfo.size()) {
            return new ArrayList<>();
        }
        List<Person> houseToAdd = new ArrayList<>();
        List<Person> senateToAdd = new ArrayList<>();
        List<Integer> addedIds = new ArrayList<>();

        for (int i = 1; i < conTermInfo.size(); i++) {
            String conTermLine = conTermInfo.get(i);
            conTermLine.replace("\"", "");
            String[] currentConLine = conTermLine.split(",");
            String conEthLine = conEthInfo.get(i);
            conEthLine.replace("\"", "");
            String[] currentEthLine = conEthLine.split(",");
            if (!currentConLine[4].equals(currentEthLine[3])) {
                return new ArrayList<>();
            }
            buildCongress(currentConLine, currentEthLine, houseToAdd, senateToAdd);
        }
        for (Person toAdd : houseToAdd) {
            addedIds.add(dao.addPerson(toAdd));
        }
        for (Person toAdd : senateToAdd) {
            addedIds.add(dao.addPerson(toAdd));
        }
        return addedIds;
    }

    public Person buildGovernor(String[] toAdd) {
        Person newPerson = new Person();
        String state = convertState(toAdd[0]);

        newPerson.setPersonId(Integer.parseInt(toAdd[3]));
        newPerson.setStateName(state);
        newPerson.setName(toAdd[4]);
        newPerson.setAge(Integer.parseInt(toAdd[9]));
        newPerson.setGender(toAdd[7]);
        newPerson.setEthnicity(toAdd[6]);
        newPerson.setParty(toAdd[8]);
        newPerson.setPosition("Governor");
        newPerson.setStartYear(convertDate(toAdd[1]));
        newPerson.setEndYear(2021);
        if (!toAdd[2].isEmpty()) {
            newPerson.setEndYear(convertDate(toAdd[2]));
        }
        return newPerson;
    }

    public Person buildMayor(String[] toAdd) {
        Person newPerson = new Person();
        String state = convertState(toAdd[1]);
        String city = convertCity(toAdd[0]);

        newPerson.setStateName(state);
        newPerson.setCityName(city);
        newPerson.setPersonId(Integer.parseInt(toAdd[2]));
        newPerson.setName(toAdd[3]);
        newPerson.setAge(Integer.parseInt(toAdd[10]));
        newPerson.setGender(toAdd[8]);
        newPerson.setEthnicity(toAdd[7]);
        newPerson.setParty(toAdd[9]);
        newPerson.setPosition("Mayor");
        newPerson.setStartYear(convertDate(toAdd[5]));
        newPerson.setEndYear(2021);
        if (!toAdd[6].isEmpty()) {
            newPerson.setEndYear(convertDate(toAdd[6]));
        }
        return newPerson;
    }

    public void buildCongress(String[] conTermInfo, String[] conEthInfo, List<Person> houseToAdd, List<Person> senateToAdd) {
        //Immutable info
        String name = convertName(conTermInfo[0], conTermInfo[1], conTermInfo[2], conTermInfo[3]);
        String gender = conTermInfo[5];
        String ethnicity = conEthInfo[4];
        Integer personId = Integer.parseInt(conEthInfo[5]);

        //Term info
        int startColumn = 6;
        String currentType = conTermInfo[startColumn];
        int earliestStart = convertDate(conTermInfo[startColumn + 1]);
        int latestEnd = convertDate(conTermInfo[startColumn + 2]);
        String currentState = convertState(conTermInfo[startColumn + 3]);
        String currentParty = conTermInfo[startColumn + 4];

        for (int term = 0; term < 30 && ((6 + (5 * term) + 4) < conTermInfo.length); term++) {
            if (!conTermInfo[startColumn + (5 * term)].isBlank()
                    && conTermInfo[startColumn + (5 * term)].equals(currentType)
                    && conTermInfo[startColumn + (5 * term) + 4].equals(currentParty)
                    && convertState(conTermInfo[startColumn + (5 * term) + 3]).equals(currentState)) {
                if (currentState == "") {
                    break;
                }
                if (convertDate(conTermInfo[startColumn + (5 * term) + 1]) < earliestStart) {
                    earliestStart = convertDate(conTermInfo[startColumn + (5 * term) + 1]);
                }
                if (convertDate(conTermInfo[startColumn + (5 * term) + 2]) > latestEnd) {
                    latestEnd = convertDate(conTermInfo[startColumn + (5 * term) + 2]);
                }

                if (term == 29 || (6 + (5 * term) + 4) == conTermInfo.length - 1) {
                    Integer age = calculateAge(conTermInfo[4], earliestStart);
                    buildPersonCongress(personId, currentState, name, age, gender, ethnicity, currentParty, currentType,
                            earliestStart, latestEnd, houseToAdd, senateToAdd);
                }

            } else {
                //Add current person and create new person
                Integer age = calculateAge(conTermInfo[4], earliestStart);
                buildPersonCongress(personId, currentState, name, age, gender, ethnicity, currentParty, currentType,
                        earliestStart, latestEnd, houseToAdd, senateToAdd);
                currentType = conTermInfo[startColumn + (5 * term)];
                earliestStart = convertDate(conTermInfo[startColumn + (5 * term) + 1]);
                latestEnd = convertDate(conTermInfo[startColumn + (5 * term) + 2]);
                currentState = convertState(conTermInfo[startColumn + (5 * term) + 3]);
                currentParty = conTermInfo[startColumn + (5 * term) + 4];
                //Add the new person if this is the last term
                if (term == 29 || (6 + (5 * term) + 4) == conTermInfo.length - 1) {
                    age = calculateAge(conTermInfo[4], earliestStart);
                    buildPersonCongress(personId, currentState, name, age, gender, ethnicity, currentParty, currentType,
                            earliestStart, latestEnd, houseToAdd, senateToAdd);
                }
            }
        }
    }

    public void addPerson() {
        Person toAdd = new Person(1000, "California", "Houston", "Test Person", 22, "Male", "White", "Republican", "Senator", 1950, 1960);
        dao.addPerson(toAdd);
    }

    public List<Person> getAllPeople() {
        return dao.getAllPeople();
    }

    private Person buildPersonCongress(Integer personId, String state, String name, Integer age, String gender, String ethnicity,
                                       String party, String position, Integer startYear, Integer endYear, List<Person> houseToAdd,
                                       List<Person> senateToAdd) {
        Person newPerson = new Person();
        newPerson.setPersonId(personId);
        newPerson.setStateName(state);
        newPerson.setName(name);
        newPerson.setAge(age);
        newPerson.setGender(gender);
        newPerson.setEthnicity(ethnicity);
        newPerson.setParty(party);
        newPerson.setStartYear(startYear);
        newPerson.setEndYear(endYear);
        if (position.equals("rep")) {
            newPerson.setPosition("House Representative");
            houseToAdd.add(newPerson);
        } else {
            newPerson.setPosition("Senator");
            senateToAdd.add(newPerson);
        }
        return newPerson;
    }

    private Integer calculateAge(String birthdate, Integer lastTerm) {
        Integer birthDate = convertBirthdate(birthdate);
        return lastTerm - birthDate;
    }

    private String convertName(String first, String middle, String last, String suffix) {
        String name = first;
        if (!middle.isBlank()) {
            name += " " + middle;
        }
        name += " " + last;
        if (!suffix.isBlank()) {
            name += " " + suffix;
        }
        return name;
    }

    private Integer convertDate(String stringDate) {
        if (stringDate.contains("-")) {
            String[] splitDate = stringDate.split("-");
            return Integer.parseInt(splitDate[0]);
        } else {
            String[] splitDate = stringDate.split("/");
            if (Integer.parseInt(splitDate[2]) > 21 ) {
                return Integer.parseInt("19" + splitDate[2]);
            } else {
                return Integer.parseInt("20" + splitDate[2]);
            }
        }
    }

    private Integer convertBirthdate(String stringDate) {
        if (stringDate.contains("-")) {
            String[] splitDate = stringDate.split("-");
            return Integer.parseInt(splitDate[0]);
        } else {
            String[] splitDate = stringDate.split("/");
            return Integer.parseInt("19" + splitDate[2]);
        }
    }

    private String convertState(String stateId) {
        String state;
        switch (stateId) {
            case "1":
            case "AL":
                state = "Alabama";
                break;
            case "2":
            case "AK":
                state = "Alaska";
                break;
            case "3":
            case "AZ":
                state = "Arizona";
                break;
            case "4":
            case "AR":
                state = "Arkansas";
                break;
            case "5":
            case "CA":
                state = "California";
                break;
            case "6":
            case "CO":
                state = "Colorado";
                break;
            case "7":
            case "CT":
                state = "Connecticut";
                break;
            case "8":
            case "DE":
                state = "Delaware";
                break;
            case "9":
            case "FL":
                state = "Florida";
                break;
            case "10":
            case "GA":
                state = "Georgia";
                break;
            case "11":
            case "HI":
                state = "Hawaii";
                break;
            case "12":
            case "ID":
                state = "Idaho";
                break;
            case "13":
            case "IL":
                state = "Illinois";
                break;
            case "14":
            case "IN":
                state = "Indiana";
                break;
            case "15":
            case "IA":
                state = "Iowa";
                break;
            case "16":
            case "KS":
                state = "Kansas";
                break;
            case "17":
            case "KY":
                state = "Kentucky";
                break;
            case "18":
            case "LA":
                state = "Louisiana";
                break;
            case "19":
            case "ME":
                state = "Maine";
                break;
            case "20":
            case "MD":
                state = "Maryland";
                break;
            case "21":
            case "MA":
                state = "Massachusetts";
                break;
            case "22":
            case "MI":
                state = "Michigan";
                break;
            case "23":
            case "MN":
                state = "Minnesota";
                break;
            case "24":
            case "MS":
                state = "Mississippi";
                break;
            case "25":
            case "MO":
                state = "Missouri";
                break;
            case "26":
            case "MT":
                state = "Montana";
                break;
            case "27":
            case "NE":
                state = "Nebraska";
                break;
            case "28":
            case "NV":
                state = "Nevada";
                break;
            case "29":
            case "NH":
                state = "New Hampshire";
                break;
            case "30":
            case "NJ":
                state = "New Jersey";
                break;
            case "31":
            case "NM":
                state = "New Mexico";
                break;
            case "32":
            case "NY":
                state = "New York";
                break;
            case "33":
            case "NC":
                state = "North Carolina";
                break;
            case "34":
            case "ND":
                state = "North Dakota";
                break;
            case "35":
            case "OH":
                state = "Ohio";
                break;
            case "36":
            case "OK":
                state = "Oklahoma";
                break;
            case "37":
            case "OR":
                state = "Oregon";
                break;
            case "38":
            case "PA":
                state = "Pennsylvania";
                break;
            case "39":
            case "RI":
                state = "Rhode Island";
                break;
            case "40":
            case "SC":
                state = "South Carolina";
                break;
            case "41":
            case "SD":
                state = "South Dakota";
                break;
            case "42":
            case "TN":
                state = "Tennessee";
                break;
            case "43":
            case "TX":
                state = "Texas";
                break;
            case "44":
            case "UT":
                state = "Utah";
                break;
            case "45":
            case "VT":
                state = "Vermont";
                break;
            case "46":
            case "VA":
                state = "Virginia";
                break;
            case "47":
            case "WA":
                state = "Washington";
                break;
            case "48":
            case "WV":
                state = "West Virginia";
                break;
            case "49":
            case "WI":
                state = "Wisconsin";
                break;
            case "50":
            case "WY":
                state = "Wyoming";
                break;
            default:
                state = "";
                break;
        }
        return state;
    }

    private String convertCity(String cityId) {
        String city;
        switch (cityId) {
            case "1":
                city = "New York City";
                break;
            case "2":
                city = "Los Angeles";
                break;
            case "3":
                city = "Chicago";
                break;
            case "4":
                city = "Houston";
                break;
            case "5":
                city = "Phoenix";
                break;
            case "6":
                city = "Philadelphia";
                break;
            case "7":
                city = "San Antonio";
                break;
            case "8":
                city = "San Diego";
                break;
            case "9":
                city = "Dallas";
                break;
            case "10":
                city = "San Jose";
                break;
            case "11":
                city = "Austin";
                break;
            case "12":
                city = "Jacksonville";
                break;
            case "13":
                city = "Fort Worth";
                break;
            case "14":
                city = "Columbus";
                break;
            case "15":
                city = "Charlotte";
                break;
            case "16":
                city = "San Francisco";
                break;
            case "17":
                city = "Indianapolis";
                break;
            case "18":
                city = "Seattle";
                break;
            case "19":
                city = "Denver";
                break;
            case "20":
                city = "Boston";
                break;
            default:
                city = "";
                break;
        }
        return city;
    }

    public List<Demographic> getDemographics() {
        return dao.getAllDemographics();
    }

    public List<Person> getbyRole(String position) {
        return dao.getPeopleByRole(position);
    }
}