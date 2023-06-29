package service;

import dao.PlayerDao;
import domain.Player;
import domain.Position;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import util.messages.ErrorMessage;
import util.messages.ResponseMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class PlayerService implements BaseBallService {

    private static PlayerService INSTANCE;
    private final PlayerDao playerDao;

    private PlayerService() {
        playerDao = PlayerDao.getInstance();
    }

    public static PlayerService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PlayerService();

        return INSTANCE;
    }

    @Override
    public void register(HashMap<String, String> map) {
        try {
            playerDao.setSqlSessionFactory(get());
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show(HashMap<String, String> map) {
        try {
            playerDao.setSqlSessionFactory(get());
            List<Player> playerList = playerDao.selectListByTeamId(Long.valueOf(map.get("teamId")));
            for (Player player : playerList)
                log.info(player);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
