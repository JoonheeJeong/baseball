package dao;

import domain.OutPlayer;
import dto.OutPlayerResponseDTO;
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
        if (newSession) {
            manageNewSession();
        }

        OutPlayerMapper mapper = session.getMapper(OutPlayerMapper.class);
        mapper.insert(outPlayer);
    }

    public void updateById(Long id, boolean newSession) {
        if (newSession) {
            manageNewSession();
        }

        OutPlayerMapper mapper = session.getMapper(OutPlayerMapper.class);
        mapper.updateById(id);
    }

    public List<OutPlayerResponseDTO> selectAll() {
        OutPlayerMapper mapper = session.getMapper(OutPlayerMapper.class);
        return mapper.selectAll();
    }
}
