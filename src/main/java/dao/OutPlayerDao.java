package dao;

import domain.OutPlayer;
import dto.OutPlayerResponseDto;
import mapper.OutPlayerMapper;

import java.util.List;

public class OutPlayerDao extends AbstractMybatisDao {

    private static final OutPlayerDao INSTANCE = new OutPlayerDao();

    private OutPlayerDao() {
    }

    public static OutPlayerDao getInstance() {
        return INSTANCE;
    }

    public void insert(OutPlayer outPlayer, boolean newSession) {
        if (newSession)
            manageNewSession();

        OutPlayerMapper mapper = session.getMapper(OutPlayerMapper.class);
        mapper.insert(outPlayer);

        PlayerDao.getInstance().session = session;
    }

    public List<OutPlayerResponseDto> selectAll() {
        OutPlayerMapper mapper = session.getMapper(OutPlayerMapper.class);
        return mapper.selectAll();
    }
}
