package Daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Profile("production")
public class PostgresDataDao {

    @Autowired
    JdbcTemplate template;

    public void buildTables() {
        template.query("INSERT INTO public.states(name) " +
                "VALUES ('Alaska');", new IdMapper());
    }

    class IdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("id");
        }
    }
}
