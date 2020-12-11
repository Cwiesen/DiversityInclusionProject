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

    public void readFile() {
        List<String> fileRows = new ArrayList<>();
        try {
            File fileData = new File("personData.csv");
            Scanner myReader = new Scanner(fileData);
            while (myReader.hasNextLine()) {
                fileRows.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (String line : fileRows) {
            String[] currentLine = line.split(",");
            System.out.println(currentLine.length);
        }
    }

    public List<Person> getAllPeople() {
        return dao.getAllPeople();
    }
}
