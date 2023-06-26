package dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

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

    @Test
    void selectAll() {
        log.info(stadiumDao.selectAll());
    }
}
