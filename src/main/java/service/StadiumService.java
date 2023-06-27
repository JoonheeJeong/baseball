package service;

import dao.StadiumDao;
import domain.Stadium;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class StadiumService implements BaseBallService {

    private final StadiumDao stadiumDao = StadiumDao.getInstance();

    public void register(HashMap<String, String> map) {
        try {
            stadiumDao.setSqlSessionFactory(get());
            Stadium stadium = Stadium.builder().name(map.get("name")).build();
            stadiumDao.insert(stadium, true);
            stadiumDao.commit();
            log.info("성공");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void show() {
        try {
            stadiumDao.setSqlSessionFactory(get());
            List<Stadium> stadiumList = stadiumDao.selectAll();
            for (Stadium stadium : stadiumList) {
                log.info(stadium);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
