package service;

import dao.StadiumDao;
import domain.Stadium;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

@Log4j2
public class StadiumServiceTest {
    static  StadiumService stadiumService;
    static StadiumDao stadiumDao;
    static HashMap<String, String> map;

    @BeforeAll
    static
    void init() {
        stadiumService = (StadiumService) StadiumService.getInstance();
        stadiumDao = StadiumDao.getInstance();
        map = new HashMap<>();
    }

    @Test
    void insertTest() {
        //given
        map.put("name", "선곡베이스볼파크");
        //when
        stadiumService.register(map);
        //then
        Assertions.assertTrue(stadiumDao.selectAll()
                .stream().map(Stadium::getName)
                .anyMatch(name -> name.equals(map.get("name"))));
    }
}