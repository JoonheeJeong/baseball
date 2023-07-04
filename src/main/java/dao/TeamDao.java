package dao;

import domain.Team;
import dto.TeamResponseDto;
import mapper.TeamMapper;

import java.util.List;

public class TeamDao extends AbstractMybatisDao {

    private static final TeamDao INSTANCE = new TeamDao();

    private TeamDao() {
    }

    public static TeamDao getInstance() {
        return INSTANCE;
    }

    public void insert(Team team, boolean newSession) {
        if (newSession)
            manageNewSession();

        TeamMapper mapper = session.getMapper(TeamMapper.class);
        mapper.insert(team);
    }

    public List<TeamResponseDto> selectAll() {
        TeamMapper mapper = session.getMapper(TeamMapper.class);
        return mapper.selectAll();
    }
}
