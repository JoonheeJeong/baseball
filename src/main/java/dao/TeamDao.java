package dao;

import domain.Team;
import mapper.TeamMapper;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TeamDao extends AbstractMybatisDao {

    private static final TeamDao INSTANCE = new TeamDao();

    private TeamDao() {
    }

    public static TeamDao getInstance() { return INSTANCE; }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        session = sqlSessionFactory.openSession();
    }

    public void insert(Team team, boolean newSession) {
        if (newSession) {
            manageNewSession();
        }

        TeamMapper mapper = session.getMapper(TeamMapper.class);
        mapper.insert(team);
    }

    public List<Team> selectAll() {
        TeamMapper mapper = session.getMapper(TeamMapper.class);
        return mapper.selectAll();
    }
}