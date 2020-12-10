package com.talentpath.DiversityBackend.services;

import com.talentpath.DiversityBackend.daos.BackendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendService {

    BackendDao dao;

    @Autowired
    public BackendService(BackendDao dao) {
        this.dao = dao;
    }

    public Integer addCity() {
        return dao.addCity();
    }

    public void readFile() {
        dao.readFile();
    }
}
