package Controllers;

import Service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    DataService service = new DataService();

    @PostMapping("/build")
    public void buildTables() {
        service.buildTables();
    }
}
