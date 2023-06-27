package dao;

import domain.Team;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamDaoTest {

    static TeamDao teamDao;

    @BeforeAll
    static void initAll() throws IOException {
        String resource = "mapper/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        teamDao = TeamDao.getInstance();
        teamDao.setSqlSessionFactory(sqlSessionFactory);
    }

    @Test
    void insert() {
        final String teamName = "삼성 라이온즈";
        Team team = Team.builder()
                .stadiumId(1L)
                .name(teamName)
                .build();

        teamDao.insert(team, true);

//        assertTrue(teamDao.selectAll().stream()
//                        .map(Team::getName)
//                        .anyMatch(name -> name.equals(teamName)));

        teamDao.rollback();
    }
}
