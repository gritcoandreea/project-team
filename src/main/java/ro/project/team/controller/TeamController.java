package ro.project.team.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.project.team.domain.ErrorResponse;
import ro.project.team.dto.TeamDto;
import ro.project.team.exception.DtoException;
import ro.project.team.exception.EntityNotFoundException;
import ro.project.team.exception.ServiceException;
import ro.project.team.service.TeamService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TeamController {

    @Autowired
    private TeamService teamService;


    @ExceptionHandler({ServiceException.class, DtoException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        if (exception instanceof DtoException || exception instanceof EntityNotFoundException) {
            log.error(exception.getMessage());
        } else {
            log.error(exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder().error(exception.getMessage()).build());
    }

    @GetMapping("/teams")
    public Map<String, List<TeamDto>> teams() throws ServiceException {
        log.debug("Entered get teams endpoint.");
        Map<String, List<TeamDto>> response = new HashMap<>();
        response.put("teams", teamService.getTeams());
        return response;
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public TeamDto teamById(@RequestParam("id") long teamId) throws DtoException, EntityNotFoundException, ServiceException {
        log.debug("Entered get team by id endpoint.");
        return teamService.getTeamById(teamId);
    }
}
