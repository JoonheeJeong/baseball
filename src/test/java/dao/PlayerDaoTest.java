package dao;

import domain.Player;
import domain.Position;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(JdbcSQLIntegrityConstraintViolationException.class, e.getCause().getClass());
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
}
