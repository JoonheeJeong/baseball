package service;

import dao.StadiumDao;
import domain.Stadium;
import lombok.extern.log4j.Log4j2;
import util.annotation.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Service
public class StadiumService implements BaseBallService {

    private static StadiumService INSTANCE;
    private final StadiumDao stadiumDao;

    private StadiumService() {
        stadiumDao = StadiumDao.getInstance();
    }

    public static StadiumService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StadiumService();
        }
        return INSTANCE;
    }

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

    public void show(HashMap<String, String> map) {
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
