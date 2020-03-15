package ro.project.team.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TeamController {

    @GetMapping("/teams")
    public Map<String, String> teams() {
        Map<String, String> resp = new HashMap<>();
        resp.put("response", "Team microservice works!");
        return resp;
    }
}
