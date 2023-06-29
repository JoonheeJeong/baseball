package controller;

import exception.IllegalParameterException;
import exception.IllegalRequestTypeException;
import service.BaseBallService;
import service.TeamService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.messages.ErrorMessage;

import java.util.HashMap;

@Controller
public class TeamController implements BaseballController {

    private BaseBallService teamService;

    public TeamController() {
        this.teamService = TeamService.getInstance();
    }

    @Override
    @RequestMapping(uri = "팀등록")
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        teamService.register(map);
    }

    @Override
    @RequestMapping(uri = "팀목록")
    public void select(String queryString) {
        if (queryString != null) {
            throw new IllegalRequestTypeException(ErrorMessage.ERR_MSG_ILLEGAL_REQUEST_TYPE);
        }
        HashMap<String, String> map = null;
        teamService.show(map);
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name") && map.containsKey("stadiumId")) {
            return true;
        }
        throw new IllegalParameterException(ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER);
    }

}
