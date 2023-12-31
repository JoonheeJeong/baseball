package service;

import dao.PlayerDao;
import domain.Player;
import domain.Position;
import dto.PositionTeamPlayerDto;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import util.messages.ErrorMessage;
import util.messages.ResponseMessage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static util.SqlSessionFactoryUtil.getSqlSessionFactory;

@Log4j2
public class PlayerService implements BaseBallService {

    private static PlayerService INSTANCE;
    private final PlayerDao playerDao;

    private PlayerService() {
        playerDao = PlayerDao.getInstance();
        playerDao.setSqlSessionFactory(getSqlSessionFactory());
    }

    public static PlayerService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PlayerService();

        return INSTANCE;
    }

    @Override
    public void register(HashMap<String, String> map) {
        try {
            Player player = Player.builder()
                    .teamId(Long.valueOf(map.get("teamId")))
                    .name(map.get("name"))
                    .position(Position.getByDescription(map.get("position")))
                    .build();
            playerDao.insert(player, true);
            playerDao.commit();
            log.info(ResponseMessage.SERVICE_SUCCESS);
        } catch (PersistenceException e) {
            log.warn(ErrorMessage.ERR_MSG_DUPLICATE_POSITION);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void show(HashMap<String, String> map) {
            List<Player> playerList = playerDao.selectListByTeamId(Long.valueOf(map.get("teamId")));
            for (Player player : playerList)
                log.info(player);
    }

    public void showByPosition() {
        try {
            PositionTeamPlayerDto positionDto= playerDao.selectListForEachTeamByPosition();
            log.info(positionDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
