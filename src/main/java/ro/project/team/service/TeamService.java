package ro.project.team.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.team.domain.Team;
import ro.project.team.dto.TeamDto;
import ro.project.team.exception.DtoException;
import ro.project.team.exception.EntityNotFoundException;
import ro.project.team.exception.ServiceException;
import ro.project.team.respository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ro.project.team.utils.DtoConverter.TEAM_DTO_TRANSFORMER;

@Service
@Slf4j
public class TeamService {

    private TeamRepository teamRepository;

    /**
     * Get all teams or empty list.
     *
     * @return List<TeamDto>
     * @throws ServiceException
     */
    public List<TeamDto> getTeams() throws ServiceException {
        try {
            List<Team> teams = teamRepository.findAll();
            return teams.stream().map(team -> {
                try {
                    return TEAM_DTO_TRANSFORMER.transformToDto(team);
                } catch (DtoException e) {
                    return null;
                }
            }).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ServiceException("Error when retrieving teams.", exception);
        }
    }

    /**
     * Get a team by id or error if team does not exist.
     *
     * @param id - long
     * @return TeamDto
     * @throws DtoException
     * @throws EntityNotFoundException
     */
    public TeamDto getTeamById(long id) throws DtoException, EntityNotFoundException, ServiceException {
        try {
            Team team = teamRepository.findById(id).orElse(null);
            if (team != null) {
                return TEAM_DTO_TRANSFORMER.transformToDto(team);
            } else {
                throw new EntityNotFoundException("Entity with id " + id + " not found!");
            }
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when retrieving team with id " + id, exception);
        }
    }


    public Team saveTeam(Team team) throws ServiceException {
        try {
            return teamRepository.save(team);
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when saving team  " + team.toString(), exception);
        }
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
