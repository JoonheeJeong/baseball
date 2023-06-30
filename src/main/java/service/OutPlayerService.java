package service;

import dao.OutPlayerDao;
import dao.PlayerDao;
import domain.OutPlayer;
import domain.Reason;
import dto.OutPlayerResponseDTO;
import lombok.extern.log4j.Log4j2;
import util.messages.ErrorMessage;
import util.messages.ResponseMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class OutPlayerService implements BaseBallService {

    private static OutPlayerService INSTANCE;
    private final OutPlayerDao outPlayerDao;
    private final PlayerDao playerDao;

    private OutPlayerService() {
        outPlayerDao = OutPlayerDao.getInstance();
        playerDao = PlayerDao.getInstance();
        try {
            playerDao.setSqlSessionFactory(get());
            outPlayerDao.setSqlSessionFactory(get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static OutPlayerService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OutPlayerService();

        return INSTANCE;
    }

    @Override
    public void register(HashMap<String, String> map) {
        OutPlayer outPlayer = OutPlayer.builder()
                .playerId(Long.valueOf(map.get("playerId")))
                .reason(Reason.getByDescription(map.get("reason")))
                .build();
        try {
            outPlayerDao.insert(outPlayer, true);
            outPlayerDao.commit();
            playerDao.updateRetiredById(Long.valueOf(map.get("playerId")), true);
            playerDao.commit();
            log.info(ResponseMessage.SERVICE_SUCCESS);
        } catch (Exception e) {
            outPlayerDao.rollback();
            playerDao.rollback();
            log.warn(ErrorMessage.ERR_MSG_TRANSACTION);
        }
    }

    @Override
    public void show() {
        List<OutPlayerResponseDTO> outPlayerList = outPlayerDao.selectAll();
        for (OutPlayerResponseDTO outPlayer : outPlayerList)
            log.info(outPlayer);
    }
}
