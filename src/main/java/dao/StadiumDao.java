package dao;

import domain.Stadium;
import mapper.StadiumMapper;

import java.util.List;

public class StadiumDao extends AbstractMybatisDao {

    private static final StadiumDao INSTANCE = new StadiumDao();

    private StadiumDao() {
    }

    public static StadiumDao getInstance() { return INSTANCE; }

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
}
