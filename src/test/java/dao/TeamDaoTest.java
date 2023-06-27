package dao;

import domain.Team;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
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

    @Test
    void selectAll() {
        List<Team> teamList = teamDao.selectAll();
        log.info(teamList);

        assertTrue(teamList.stream()
                .map(Team::getName)
                .anyMatch(name -> name.equals("한화 이글스")));
        assertTrue(teamList.stream()
                .map(Team::getName)
                .anyMatch(name -> name.equals("두산 베어스")));
        assertTrue(teamList.stream()
                .map(Team::getName)
                .anyMatch(name -> name.equals("롯데 자이언츠")));
    }
}
