package dao;

import domain.Player;
import domain.Team;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class PlayerDaoTest {

    static PlayerDao playerDao;

    @BeforeAll
    static void initAll() throws IOException {
        playerDao = PlayerDao.getInstance();
        playerDao.setSqlSessionFactory(SqlSessionFactoryUtil.get());
    }

    @Test
    void insert() {
        final String playerName = "정준희";
        Player player = Player.builder()
                .teamId(1L)
                .name(playerName)
                .position("유격수")
                .build();

        Assertions.assertThrows(
                Exception.class,
                () -> playerDao.insert(player, true));
    }
}
