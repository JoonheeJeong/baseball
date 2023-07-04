package service;

import dao.OutPlayerDao;
import dao.PlayerDao;
import domain.OutPlayer;
import domain.Reason;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class OutPlayerServiceTest {

    private static OutPlayerService outPlayerService;
    private static OutPlayerDao outPlayerDao;
    private static PlayerDao playerDao;

    @BeforeAll
    static void initAll() {
        outPlayerService = OutPlayerService.getInstance();
        outPlayerDao = OutPlayerDao.getInstance();
        playerDao = PlayerDao.getInstance();
    }

    @Test
    void register() {
        // given
        HashMap<String, String> map = new HashMap<>();
        map.put("playerId", "1");
        map.put("reason", "은퇴");

        // when
        // outPlayerService.register(paramMap);
        // 즉시 커밋해버려서 테스트에 쓸 수 없음

        OutPlayer outPlayer = OutPlayer.builder()
                .playerId(Long.valueOf(map.get("playerId")))
                .reason(Reason.getByDescription(map.get("reason")))
                .build();
        outPlayerDao.insert(outPlayer, true);
        playerDao.updateRetiredById(Long.valueOf(map.get("playerId")));

        // then
        outPlayerService.show();
        // 마지막 줄 이태양 은퇴

        // reset
        playerDao.rollback();
        outPlayerDao.rollback();
    }
}
