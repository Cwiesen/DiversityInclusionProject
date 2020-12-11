package com.talentpath.DiversityBackend.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Profile("production")
public class PostgresDao implements BackendDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public int addCity() {
       List<Integer> idList = template.query("INSERT INTO public.states(name) " +
                "VALUES ('Florida') " + " returning \"id\"", new IdMapper());

       return idList.get(0);
    }

    class IdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("id");
        }


    }
}
