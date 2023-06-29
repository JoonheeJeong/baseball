package service;

import dao.TeamDao;
import domain.Team;
import dto.TeamResponseDTO;
import util.messages.ErrorMessage;
import lombok.extern.log4j.Log4j2;
import util.messages.ResponseMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class TeamService implements BaseBallService {

    private static TeamService INSTANCE;
    private final TeamDao teamDao;

    private TeamService() {
        teamDao = TeamDao.getInstance();
    }

    public static TeamService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeamService();
        }
        return INSTANCE;
    }

    @Override
    public void register(HashMap<String, String> map) {
        try {
            teamDao.setSqlSessionFactory(get());
            Team team = Team.builder()
                    .stadiumId(Long.valueOf(map.get("stadiumId")))
                    .name(map.get("name"))
                    .build();
            teamDao.insert(team, true);
            teamDao.commit();
            log.info(ResponseMessage.SERVICE_SUCCESS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show(HashMap<String, String> map) {
        try {
            teamDao.setSqlSessionFactory(get());
            List<TeamResponseDTO> teamList = teamDao.selectAll();
            for (TeamResponseDTO teamResponseDTO : teamList) {
                log.info(teamResponseDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
