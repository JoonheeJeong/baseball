package mapper;

import domain.Team;
import dto.TeamResponseDTO;

import java.util.List;

public interface TeamMapper {
    void insert(Team team);
    List<TeamResponseDTO> selectAll();
}
