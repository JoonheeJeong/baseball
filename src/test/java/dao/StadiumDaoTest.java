package dao;

import lombok.extern.log4j.Log4j2;
import domain.Stadium;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class StadiumDaoTest {

    static StadiumDao stadiumDao;

    @BeforeAll
    static void init() throws IOException {
        stadiumDao = StadiumDao.getInstance();
        stadiumDao.setSqlSessionFactory(SqlSessionFactoryUtil.get());
    }

    @AfterEach
    void afterEach() {
        stadiumDao.rollback();
    }

    @Test
    void insert() {
        final String nameToFind = "대구 삼성 라이온즈 파크";
        Stadium stadiumInserted = Stadium.builder()
                .name(nameToFind)
                .build();
        stadiumDao.insert(stadiumInserted, true);

        assertTrue(stadiumDao.selectAll().stream()
                .map(Stadium::getName)
                .anyMatch(name -> name.equals(nameToFind)));
    }

    @Test
    void selectAll() {
        List<Stadium> stadiumList = stadiumDao.selectAll();
        log.info(stadiumList);

        assertTrue(stadiumList.stream()
                .map(Stadium::getName)
                .anyMatch(name -> name.equals("대전 한화생명 이글스파크")));
        assertTrue(stadiumList.stream()
                .map(Stadium::getName)
                .anyMatch(name -> name.equals("잠실종합운동장")));
        assertTrue(stadiumList.stream()
                .map(Stadium::getName)
                .anyMatch(name -> name.equals("사직 야구장")));
    }
}
