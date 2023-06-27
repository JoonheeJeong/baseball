package dao;

import lombok.extern.log4j.Log4j2;
import model.Stadium;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class StadiumDaoTest {

    static StadiumDao stadiumDao;

    @BeforeAll
    static void init() throws IOException {
        String resource = "mapper/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        stadiumDao = StadiumDao.getInstance();
        stadiumDao.setSqlSessionFactory(sqlSessionFactory);
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
        log.info(stadiumDao.selectAll());
    }
}
