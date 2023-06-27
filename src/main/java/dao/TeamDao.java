package dao;

import domain.Team;
import mapper.TeamMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeamDao {

    private static final TeamDao INSTANCE = new TeamDao();

    private TeamDao() {
    }

    public static TeamDao getInstance() { return INSTANCE; }

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

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

    public void rollback() {
        session.rollback();
    }

    private void manageNewSession() {
        session.close();
        session = sqlSessionFactory.openSession();
    }
}
