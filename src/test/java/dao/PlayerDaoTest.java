package dao;

import domain.Player;
import domain.Position;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.SqlSessionFactoryUtil;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class PlayerDaoTest {

    static final String playerName = "정준희";

    static Player playerToInsert;
    static PlayerDao playerDao;

    @BeforeAll
    static void initAll() {
        playerDao = PlayerDao.getInstance();
        playerDao.setSqlSessionFactory(SqlSessionFactoryUtil.getSqlSessionFactory());

        playerToInsert = Player.builder()
                .name(playerName)
                .position(Position.SS)
                .build();
    }

    @Test
    void insertDuplicatePosition() {
        playerToInsert.setTeamId(1L);

        Exception e = assertThrows(
                PersistenceException.class,
                () -> playerDao.insert(playerToInsert, true));
        assertEquals(SQLIntegrityConstraintViolationException.class, e.getCause().getClass());
    }

    @Test
    void insert() {
        playerToInsert.setTeamId(4L);

        playerDao.insert(playerToInsert, true);

        playerDao.rollback();
    }

    @Test
    void selectListByTeamId() {
        final Long[] teamIdToSearch = { 1L, 4L };
        final int[] numPlayersOfTeam = { 9,  1 };

        for (int i = 0; i < 2; ++i) {
            List<Player> playerList = playerDao.selectListByTeamId(teamIdToSearch[i]);
            assertEquals(numPlayersOfTeam[i], playerList.size());
            assertEquals(teamIdToSearch[i], playerList.get(0).getTeamId());
        }
    }

    @Test
    void selectListByTeamId_NotFound() {
        final Long teamIdToSearch = 100L;

        List<Player> playerList = playerDao.selectListByTeamId(teamIdToSearch);

        assertEquals(0, playerList.size());
    }

    @Test
    void selectListForEachTeamByPosition() throws SQLException {
        log.info(playerDao.selectListForEachTeamByPosition());
    }

    @Test
    void updateRetiredById() {
        final Long retiredPlayerId = 28L;

        playerDao.updateRetiredById(retiredPlayerId);

        List<Player> playerList = playerDao.selectListByTeamId(4L);
        Optional<Long> idOptional = playerList.stream()
                .map(Player::getId)
                .filter(id -> id.equals(retiredPlayerId))
                .findAny();
        assertTrue(idOptional.isEmpty());

        playerDao.rollback();
    }
}
