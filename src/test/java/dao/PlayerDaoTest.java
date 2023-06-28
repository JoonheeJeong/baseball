package dao;

import domain.Player;
import domain.Position;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
public class PlayerDaoTest {

    static PlayerDao playerDao;

    @BeforeAll
    static void initAll() throws IOException {
        playerDao = PlayerDao.getInstance();
        playerDao.setSqlSessionFactory(SqlSessionFactoryUtil.get());
    }

    @Test
    void insertDuplicatePosition() {
        final String playerName = "정준희";
        Player player = Player.builder()
                .teamId(1L)
                .name(playerName)
                .position(Position.SS)
                .build();

        Exception e = assertThrows(
                PersistenceException.class,
                () -> playerDao.insert(player, true));
        assertEquals(JdbcSQLIntegrityConstraintViolationException.class, e.getCause().getClass());
    }
}
