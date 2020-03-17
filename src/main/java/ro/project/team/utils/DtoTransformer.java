package ro.project.team.utils;

import ro.project.team.domain.Robot;
import ro.project.team.domain.Team;
import ro.project.team.dto.TeamDto;
import ro.project.team.exception.DtoException;

import java.util.stream.Collectors;

public interface DtoTransformer<P, D> {
    D transformToDto(P pojo) throws DtoException;

    TeamDtoTransformer TEAM_DTO_TRANSFORMER = new TeamDtoTransformer();


    class TeamDtoTransformer implements DtoTransformer<Team, TeamDto> {

        @Override
        public TeamDto transformToDto(Team pojo) throws DtoException {
            if (pojo != null) {
                TeamDto teamDto = new TeamDto();
                teamDto.setId(pojo.getId());
                teamDto.setName(pojo.getName());
                teamDto.setCaptainName(pojo.getCaptain().getName());
                teamDto.setRobots(pojo.getRobots().stream().map(Robot::getName).collect(Collectors.toSet()));
                return teamDto;
            } else {
                throw new DtoException("Team object is null!");
            }

        }

    }
}
