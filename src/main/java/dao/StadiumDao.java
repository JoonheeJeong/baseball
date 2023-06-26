package dao;

import mapper.StadiumMapper;
import model.Stadium;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StadiumDao {

    private static final StadiumDao INSTANCE = new StadiumDao();

    private StadiumDao() {
    }

    public static StadiumDao getInstance() { return INSTANCE; }

    private SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Stadium> selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            StadiumMapper mapper = session.getMapper(StadiumMapper.class);
            return mapper.selectAll();
        }
    }
}
