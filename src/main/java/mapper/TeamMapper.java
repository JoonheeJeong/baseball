package mapper;

import domain.Team;
import dto.TeamResponseDto;

import java.util.List;

public interface TeamMapper {
    void insert(Team team);
    List<TeamResponseDto> selectAll();
}
