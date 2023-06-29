package dao;

import domain.Player;
import domain.Position;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class PlayerDaoTest {

    static final String playerName = "정준희";

    static Player playerToInsert;
    static PlayerDao playerDao;

    @BeforeAll
    static void initAll() throws IOException {
        playerDao = PlayerDao.getInstance();
        playerDao.setSqlSessionFactory(SqlSessionFactoryUtil.get());

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
//        assertEquals(JdbcSQLIntegrityConstraintViolationException.class, e.getCause().getClass());
    }

    @Test
    void insert() {
        playerToInsert.setTeamId(4L);

        playerDao.insert(playerToInsert, true);

        playerDao.rollback();
    }

    @Test
    void selectListByTeamId() {
        final Long teamIdToSearch = 1L;

        List<Player> playerList = playerDao.selectListByTeamId(teamIdToSearch);

        assertEquals(9, playerList.size());
        assertEquals(teamIdToSearch, playerList.get(0).getTeamId());
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

        playerDao.updateRetiredById(retiredPlayerId, true);

        List<Player> playerList = playerDao.selectListByTeamId(4L);
        Optional<Long> idOptional = playerList.stream()
                .map(Player::getId)
                .filter(id -> id.equals(retiredPlayerId))
                .findAny();
        assertTrue(idOptional.isEmpty());

        playerDao.rollback();
    }
}
