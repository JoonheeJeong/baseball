package service;

import dao.StadiumDao;
import domain.Stadium;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

class StadiumServiceTest {

    private static StadiumDao stadiumDao;
    private static StadiumService stadiumService;

    @BeforeAll
    static void init() throws IOException {
        String resource = "mapper/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        stadiumDao = StadiumDao.getInstance();
        stadiumDao.setSqlSessionFactory(sqlSessionFactory);
        stadiumService = new StadiumService();
    }


    @Test
    void registerStadium() {

        //given
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "고척스카이돔12");
        //when
        stadiumService.register(map);
        //then
    }

    @Test
    void showStadium() {

        //given
        Stadium stadium1 = Stadium.builder().id((long) 1).name("Stadium1").build();
        Stadium stadium2 = Stadium.builder().id((long) 2).name("Stadium2").build();
        Stadium stadium3 = Stadium.builder().id((long) 3).name("Stadium2").build();
        Stadium stadium4 = Stadium.builder().id((long) 4).name("Stadium2").build();
        //when
        //stadiumService.show();
    }
}