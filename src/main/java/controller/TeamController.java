package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.TeamService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

import static util.messages.ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER;

@Controller
public class TeamController implements BaseballController {

    private final BaseBallService teamService;

    public TeamController() {
        this.teamService = TeamService.getInstance();
    }

    @Override
    @RequestMapping(uri = "팀등록", method = RequestMethod.POST)
    public void add(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        teamService.register(map);
    }

    @Override
    @RequestMapping(uri = "팀목록", method = RequestMethod.GET)
    public void showList() {
        teamService.show();
    }

    @Override
    public void validateParameter(HashMap<String, String> map) {
        if (!(map.containsKey("name") && map.containsKey("stadiumId")))
            throw new IllegalParameterException(ERR_MSG_ILLEGAL_PARAMETER);
    }
}
