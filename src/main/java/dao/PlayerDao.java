package dao;

import domain.Player;
import mapper.PlayerMapper;
import org.apache.ibatis.session.SqlSessionFactory;

public class PlayerDao extends AbstractMybatisDao {

    private static final PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    public static PlayerDao getInstance() { return INSTANCE; }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        session = sqlSessionFactory.openSession();
    }

    public void insert(Player player, boolean newSession) {
        if (newSession) {
            manageNewSession();
        }

        PlayerMapper mapper = session.getMapper(PlayerMapper.class);
        mapper.insert(player);
    }
}
