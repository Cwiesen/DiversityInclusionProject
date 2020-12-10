package Service;

import Daos.PostgresDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    PostgresDataDao dao = new PostgresDataDao();

    public void buildTables() {
        dao.buildTables();
    }
}
