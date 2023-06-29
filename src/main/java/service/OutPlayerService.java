package service;

import dao.OutPlayerDao;
import domain.OutPlayer;
import domain.Reason;
import dto.OutPlayerResponseDTO;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class OutPlayerService implements BaseBallService{

    private static OutPlayerService INSTANCE;
    private final OutPlayerDao outPlayerDao;

    private OutPlayerService() {
        outPlayerDao = OutPlayerDao.getInstance();
    }

    public static OutPlayerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutPlayerService();
        }
        return INSTANCE;
    }
    @Override
    public void register(HashMap<String, String> map) {
        try {
            outPlayerDao.setSqlSessionFactory(get());
            OutPlayer.builder()
                    .playerId(Long.valueOf(map.get("playerId")))
                    .reason(Reason.getByDescription(map.get("reason")))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show(HashMap<String, String> map) {
        try {
            outPlayerDao.setSqlSessionFactory(get());
            List<OutPlayerResponseDTO> outPlayerList = outPlayerDao.selectAll();
            for (OutPlayerResponseDTO outPlayer : outPlayerList) {
                log.info(outPlayer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
