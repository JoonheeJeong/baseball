package dao;

import mapper.StadiumMapper;
import domain.Stadium;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StadiumDao {

    private static final StadiumDao INSTANCE = new StadiumDao();

    private StadiumDao() {
    }

    public static StadiumDao getInstance() { return INSTANCE; }

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        session = sqlSessionFactory.openSession();
    }

    public void insert(Stadium stadium, boolean newSession) {
        if (newSession) {
            manageNewSession();
        }

        StadiumMapper mapper = session.getMapper(StadiumMapper.class);
        mapper.insert(stadium);
    }

    public List<Stadium> selectAll() {
        StadiumMapper mapper = session.getMapper(StadiumMapper.class);
        return mapper.selectAll();
    }

    public void rollback() {
        session.rollback();
    }

    public void commit() {
        session.commit();
    }

    private void manageNewSession() {
        session.close();
        session = sqlSessionFactory.openSession();
    }
}
