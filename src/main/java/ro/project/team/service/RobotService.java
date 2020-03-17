package ro.project.team.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.team.domain.Robot;
import ro.project.team.domain.Team;
import ro.project.team.respository.RobotRepository;

@Service
@Slf4j
public class RobotService {

    private RobotRepository robotRepository;

    public Robot saveRobot(Robot robot) {
        try {
            return robotRepository.save(robot);
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when saving robot  " + robot.toString(), exception);
        }
    }

    @Autowired
    public void setRobotRepository(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }
}
