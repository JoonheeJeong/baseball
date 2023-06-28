package service;

import dao.TeamDao;
import domain.Team;
import dto.TeamResponseDTO;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class TeamService implements BaseBallService{

    private final TeamDao teamDao = TeamDao.getInstance();
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
            log.info("성공");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show() {
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
