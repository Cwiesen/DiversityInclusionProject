package com.talentpath.DiversityBackend.daos;

import com.talentpath.DiversityBackend.models.Demographic;
import com.talentpath.DiversityBackend.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("production")
public class PostgresDao implements BackendDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Integer addPerson(Person person) {
        List<Integer> idList = template.query("INSERT INTO public.\"Persons\"(\n" +
                "\t state, city, age, gender, ethnicity, party, \"position\", \"startYear\", \"endYear\")\n" +
                "\tVALUES ('"+person.getStateName()+"', '"+person.getCityName()+"', " +
                "'"+person.getAge()+"', '"+person.getGender()+"', '"+person.getEthnicity()+"" +
                "', '"+person.getParty()+"', '"+person.getPosition()+"" +
                "', '"+person.getStartYear()+"', '"+person.getEndYear()+"" +
                "') " + "returning \"id\";",new IdMapper());
        return idList.get(0);
    }

    @Override
    public Integer addDemographic(Demographic demographic) {
        List<Integer> idList = template.query("INSERT INTO public.\"Demographics\"(\n" +
                        "\t year, city, state, \"percentFemale\", \"percentWhite\", \n" +
                        "\t\"percentAfricanAmerican\", \"percentAsian\", \"percentHispanic\", \n" +
                        "\t\"percentNativeAmerican\", age0_17, age18_24, age25_44, age45_64, \"age65Plus\", population)\n" +
                        "\tVALUES ('"+demographic.getYear()+"', '"+demographic.getCity() +"'" +
                        ", '"+demographic.getState()+"', '"+demographic.getPercentFemale()+"', " +
                        "'"+demographic.getPercentWhite()+"', '"+demographic.getPercentAfricanAmerican()+
                        "', '"+demographic.getPercentAsian()+"', " +
                        "'"+demographic.getPercentHispanic()+"', '"+demographic.getPercentNativeAmerican()+
                        "', '"+demographic.getAge0_17()+"', " +
                        "'"+demographic.getAge18_24()+"', '"+demographic.getAge25_44()+
                        "', '"+demographic.getAge45_64()+"', " +
                        "'"+demographic.getAge65Plus()+"', '"+demographic.getPopulation()+"') returning \"id\";",
                new IdMapper());
        return idList.get(0);
    }

    @Override
    public List<Person> getAllPeople() {
        return template.query("select * from \"Persons\";", new PersonMapper());
    }

    @Override
    public List<Demographic> getAllDemographics() {
        return template.query("select * from \"Demographics\";", new DemographicMapper());
    }

    @Override
    public List<Person> getPeopleByYear(Integer year) {
        return template.query("Select * from \"Persons\"\n" +
                "\n" +
                "Where "+ year +" BETWEEN \"startYear\" AND \"endYear\";",new PersonMapper());
    }

    @Override
    public List<Person> getPeopleByRole(String role) {
        return template.query("Select * from \"Persons\"\n" +
                "\n" +
                "Where \"position\" = '"+role+"';",new PersonMapper());
    }

    @Override
    public List<Person> getPeopleByRoleAndYear(String role, Integer year) {
        return template.query("Select * from \"Persons\"\n" +
                "\n" +
                "Where "+year+" BETWEEN \"startYear\" AND \"endYear\"\n" +
                "\n" +
                "AND\n" +
                "\n" +
                "\"position\" = '"+role+"';",new PersonMapper());
    }

    @Override
    public List<Person> getPeopleByRoleYearAndCity(String role, String year, String city, String state) {
        return null;
    }

    @Override
    public List<Person> getPeopleByCity(String city, String state) {
        return null;
    }

    @Override
    public List<Person> getPeopleByState(String state) {
        return null;
    }

    @Override
    public List<Person> getPeopleByStateAndYear(String state, String year) {
        return null;
    }


    class IdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("id");
        }


    }

    class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person toReturn = new Person();
            toReturn.setId(resultSet.getInt("id"));
            toReturn.setAge(resultSet.getInt("age"));
            toReturn.setCityName(resultSet.getString("city"));
            toReturn.setStateName(resultSet.getString("state"));
            toReturn.setGender(resultSet.getString("gender"));
            toReturn.setEthnicity(resultSet.getString("ethnicity"));
            toReturn.setParty(resultSet.getString("party"));
            toReturn.setPosition(resultSet.getString("position"));
            toReturn.setStartYear(resultSet.getInt("startYear"));
            toReturn.setEndYear(resultSet.getInt("endYear"));
            return toReturn;

        }
    }

    class DemographicMapper implements RowMapper<Demographic> {

        @Override
        public Demographic mapRow(ResultSet resultSet, int i) throws SQLException {
            Demographic toReturn = new Demographic();
            toReturn.setId(resultSet.getInt("id"));
            toReturn.setYear(resultSet.getInt("year"));
            toReturn.setCity(resultSet.getString("city"));
            toReturn.setState(resultSet.getString("state"));
            toReturn.setPercentFemale(resultSet.getInt("percentFemale"));
            toReturn.setPercentWhite(resultSet.getInt("percentWhite"));
            toReturn.setPercentAfricanAmerican(resultSet.getInt("percentAfricanAmerican"));
            toReturn.setPercentAsian(resultSet.getInt("percentAsian"));
            toReturn.setPercentHispanic(resultSet.getInt("percentHispanic"));
            toReturn.setPercentNativeAmerican(resultSet.getInt("percentNativeAmerican"));
            toReturn.setAge0_17(resultSet.getInt("age0_17"));
            toReturn.setAge18_24(resultSet.getInt("age18_24"));
            toReturn.setAge25_44(resultSet.getInt("age25_44"));
            toReturn.setAge45_64(resultSet.getInt("age45_64"));
            toReturn.setAge65Plus(resultSet.getInt("age65Plus"));
            toReturn.setPopulation(resultSet.getInt("population"));
            return toReturn;

        }
    }

}
