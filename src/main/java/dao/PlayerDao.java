package dao;

import domain.Player;
import mapper.PlayerMapper;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PlayerDao extends AbstractMybatisDao {

    private static final PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    public static PlayerDao getInstance() { return INSTANCE; }

    public void insert(Player player, boolean newSession) {
        if (newSession) {
            manageNewSession();
        }

        PlayerMapper mapper = session.getMapper(PlayerMapper.class);
        mapper.insert(player);
    }

    public List<Player> selectListByTeamId(Long teamId) {
        PlayerMapper mapper = session.getMapper(PlayerMapper.class);
        return mapper.selectListByTeamId(teamId);
    }
}
