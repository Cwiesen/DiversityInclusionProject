package com.talentpath.DiversityBackend.services;

import com.talentpath.DiversityBackend.daos.BackendDao;
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

    public List<Integer> readFile() {
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
        newPerson.setName(toAdd[2]);
        newPerson.setAge(Integer.parseInt(toAdd[8]));
        newPerson.setGender(toAdd[6]);
        newPerson.setEthnicity(toAdd[5]);
        newPerson.setParty(toAdd[7]);
        newPerson.setPosition("Mayor");
        newPerson.setStartYear(convertDate(toAdd[1]));
        newPerson.setEndYear(-1);
        if (!toAdd[2].isEmpty()) {
            newPerson.setEndYear(convertDate(toAdd[2]));
        }
        return newPerson;
    }

    public void addPerson() {
        Person toAdd = new Person(1000, "California", "Houston", "Test Person", 22, "Male", "White", "Republican", "Senator", 1950, 1960);
        dao.addPerson(toAdd);
    }

    public List<Person> getAllPeople() {
        return dao.getAllPeople();
    }

    private Integer convertDate(String stringDate) {
        String[] splitDate = stringDate.split("/");
        if (Integer.parseInt(splitDate[2]) > 21 ) {
            return Integer.parseInt("19" + splitDate[2]);
        } else {
            return Integer.parseInt("20" + splitDate[2]);
        }
    }

    private String convertState(String stateId) {
        String state;
        switch (stateId) {
            case "1":
                state = "Alabama";
                break;
            case "2":
                state = "Alaska";
                break;
            case "3":
                state = "Arizona";
                break;
            case "4":
                state = "Arkansas";
                break;
            case "5":
                state = "California";
                break;
            case "6":
                state = "Colorado";
                break;
            case "7":
                state = "Connecticut";
                break;
            case "8":
                state = "Delaware";
                break;
            case "9":
                state = "Florida";
                break;
            case "10":
                state = "Georgia";
                break;
            case "11":
                state = "Hawaii";
                break;
            case "12":
                state = "Idaho";
                break;
            case "13":
                state = "Illinois";
                break;
            case "14":
                state = "Indiana";
                break;
            case "15":
                state = "Iowa";
                break;
            case "16":
                state = "Kansas";
                break;
            case "17":
                state = "Kentucky";
                break;
            case "18":
                state = "Louisiana";
                break;
            case "19":
                state = "Maine";
                break;
            case "20":
                state = "Maryland";
                break;
            case "21":
                state = "Massachusetts";
                break;
            case "22":
                state = "Michigan";
                break;
            case "23":
                state = "Minnesota";
                break;
            case "24":
                state = "Mississippi";
                break;
            case "25":
                state = "Missouri";
                break;
            case "26":
                state = "Montana";
                break;
            case "27":
                state = "Nebraska";
                break;
            case "28":
                state = "Nevada";
                break;
            case "29":
                state = "New Hampshire";
                break;
            case "30":
                state = "New Jersey";
                break;
            case "31":
                state = "New Mexico";
                break;
            case "32":
                state = "New York";
                break;
            case "33":
                state = "North Carolina";
                break;
            case "34":
                state = "North Dakota";
                break;
            case "35":
                state = "Ohio";
                break;
            case "36":
                state = "Oklahoma";
                break;
            case "37":
                state = "Oregon";
                break;
            case "38":
                state = "Pennsylvania";
                break;
            case "39":
                state = "Rhode Island";
                break;
            case "40":
                state = "South Carolina";
                break;
            case "41":
                state = "South Dakota";
                break;
            case "42":
                state = "Tennessee";
                break;
            case "43":
                state = "Texas";
                break;
            case "44":
                state = "Utah";
                break;
            case "45":
                state = "Vermont";
                break;
            case "46":
                state = "Virginia";
                break;
            case "47":
                state = "Washington";
                break;
            case "48":
                state = "West Virginia";
                break;
            case "49":
                state = "Wisconsin";
                break;
            case "50":
                state = "Wisconsin";
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
}