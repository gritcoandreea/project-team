package ro.project.team.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.project.team.domain.Captain;

@Repository
public interface CaptainRepository extends JpaRepository<Captain,Long> {
}
