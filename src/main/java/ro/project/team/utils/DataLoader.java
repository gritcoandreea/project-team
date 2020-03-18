package ro.project.team.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.project.team.domain.Captain;
import ro.project.team.domain.Robot;
import ro.project.team.domain.Team;
import ro.project.team.exception.ServiceException;
import ro.project.team.service.CaptainService;
import ro.project.team.service.RobotService;
import ro.project.team.service.TeamService;

import java.util.*;

/**
 * Utility class to add data in H2 database.
 */
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private TeamService teamService;
    private CaptainService captainService;
    private RobotService robotService;

    @Override
    public void run(String... args) throws ServiceException {
        log.info("Loading data starting...");
        List<Team> teams = getTeams();
        createCaptains(teams);
        createRobots(teams);
        log.info("Data persisted successfully in H2 db.");
    }

    private List<Team> getTeams() throws ServiceException {
        List<Team> teams = new ArrayList<>();
        teams.add(teamService.saveTeam(Team.builder().name("Team1").build()));
        teams.add(teamService.saveTeam(Team.builder().name("Team2").build()));
        teams.add(teamService.saveTeam(Team.builder().name("Team3").build()));
        return teams;
    }


    private void createCaptains(List<Team> teams) {
        List<Captain> captains = new ArrayList<>();
        captains.add(Captain.builder().name("Captain1").team(teams.get(0)).build());
        captains.add(Captain.builder().name("Captain2").team(teams.get(1)).build());
        captains.add(Captain.builder().name("Captain3").team(teams.get(2)).build());
        captains.forEach(captain -> captainService.saveCaptain(captain));
    }

    private void createRobots(List<Team> teams) {
        List<Robot> robots = new ArrayList<>();
        robots.add(Robot.builder().name("Robot1").team(teams.get(0)).build());
        robots.add(Robot.builder().name("Robot2").team(teams.get(0)).build());
        robots.add(Robot.builder().name("Robot3").team(teams.get(1)).build());
        robots.add(Robot.builder().name("Robot4").team(teams.get(1)).build());
        robots.add(Robot.builder().name("Robot5").team(teams.get(2)).build());
        robots.add(Robot.builder().name("Robot6").team(teams.get(2)).build());
        robots.forEach(robot -> robotService.saveRobot(robot));
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    public void setCaptainService(CaptainService captainService) {
        this.captainService = captainService;
    }

    @Autowired
    public void setRobotService(RobotService robotService) {
        this.robotService = robotService;
    }
}
