package ro.project.team.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.team.domain.Captain;
import ro.project.team.respository.CaptainRepository;

@Service
@Slf4j
public class CaptainService {

    private CaptainRepository captainRepository;

    public Captain saveCaptain(Captain captain) {
        try {
            return captainRepository.save(captain);
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when saving captain  " + captain.toString(), exception);
        }
    }

    @Autowired
    public void setCaptainRepository(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }
}
