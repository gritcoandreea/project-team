package ro.project.team.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.project.team.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
}
