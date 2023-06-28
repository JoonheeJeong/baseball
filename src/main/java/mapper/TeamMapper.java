package mapper;

import domain.Team;
import domain.PositionTeamPlayer;

import java.util.List;

public interface TeamMapper {
    void insert(Team team);
    List<Team> selectAll();
}
