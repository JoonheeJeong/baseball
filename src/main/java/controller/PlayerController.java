package controller;

import util.messages.ErrorMessage;
import exception.IllegalParameterException;
import service.BaseBallService;
import service.PlayerService;
import util.annotation.Controller;
import util.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class PlayerController implements BaseballController {
    private BaseBallService playerService;

    public PlayerController() {
        this.playerService = PlayerService.getInstance();
    }

    @Override
    @RequestMapping(uri = "선수등록")
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        playerService.register(map);
    }

    @Override
    @RequestMapping(uri = "선수목록")
    public void select(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        playerService.show(map);
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name") && map.containsKey("teamId") && map.containsKey("position") && map.size() == 3) {
            return true;
        }
        throw new IllegalParameterException(ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER);
    }
}
