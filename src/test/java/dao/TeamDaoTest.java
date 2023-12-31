package dao;

import domain.Team;
import dto.TeamResponseDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.SqlSessionFactoryUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class TeamDaoTest {

    static TeamDao teamDao;

    @BeforeAll
    static void initAll() {
        teamDao = TeamDao.getInstance();
        teamDao.setSqlSessionFactory(SqlSessionFactoryUtil.getSqlSessionFactory());
    }

    @Test
    void insert() {
        final String teamName = "삼성 라이온즈";
        Team team = Team.builder()
                .stadiumId(1L)
                .name(teamName)
                .build();

        log.info(team);
        teamDao.insert(team, true);

        assertTrue(teamDao.selectAll().stream()
                .map(TeamResponseDto::getTeamName)
                .anyMatch(name -> name.equals(teamName)));

        teamDao.rollback();
    }

    @Test
    void selectAll() {
        List<TeamResponseDto> teamList = teamDao.selectAll();
        log.info(teamList);

        assertTrue(teamList.stream()
                .map(TeamResponseDto::getTeamName)
                .anyMatch(name -> name.equals("한화 이글스")));
        assertTrue(teamList.stream()
                .map(TeamResponseDto::getTeamName)
                .anyMatch(name -> name.equals("두산 베어스")));
        assertTrue(teamList.stream()
                .map(TeamResponseDto::getTeamName)
                .anyMatch(name -> name.equals("롯데 자이언츠")));
    }
}
