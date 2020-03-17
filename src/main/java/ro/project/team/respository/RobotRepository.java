package ro.project.team.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.project.team.domain.Robot;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {
}