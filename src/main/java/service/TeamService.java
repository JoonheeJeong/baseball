package service;

import dao.TeamDao;
import domain.Team;
import dto.TeamResponseDto;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.exceptions.PersistenceException;
import util.messages.ErrorMessage;
import util.messages.ResponseMessage;

import java.util.HashMap;
import java.util.List;

import static util.SqlSessionFactoryUtil.getSqlSessionFactory;

@Log4j2
public class TeamService implements BaseBallService {

    private static TeamService INSTANCE;
    private final TeamDao teamDao;

    private TeamService() {
        teamDao = TeamDao.getInstance();
        teamDao.setSqlSessionFactory(getSqlSessionFactory());
    }

    public static TeamService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TeamService();

        return INSTANCE;
    }

    @Override
    public void register(HashMap<String, String> map) {
        try {
            Team team = Team.builder()
                    .stadiumId(Long.valueOf(map.get("stadiumId")))
                    .name(map.get("name"))
                    .build();
            teamDao.insert(team, true);
            teamDao.commit();
            log.info(ResponseMessage.SERVICE_SUCCESS);
        } catch (PersistenceException e) {
            log.warn(ErrorMessage.ERR_MSG_DUPLICATE_PARAMETER);
        }
    }

    @Override
    public void show() {
            List<TeamResponseDto> teamList = teamDao.selectAll();
            for (TeamResponseDto teamResponseDTO : teamList)
                log.info(teamResponseDTO);
    }
}
