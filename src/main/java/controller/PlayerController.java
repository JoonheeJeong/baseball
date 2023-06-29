package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.PlayerService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

import static util.messages.ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER;

@Controller
public class PlayerController implements BaseballController {
    private final BaseBallService playerService;

    public PlayerController() {
        this.playerService = PlayerService.getInstance();
    }

    @Override
    @RequestMapping(uri = "선수등록", method = RequestMethod.POST)
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        playerService.register(map);
    }

    @Override
    public void select() {

    }

    @Override
    @RequestMapping(uri = "선수목록", method = RequestMethod.GET)
    public void select(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        playerService.show(map);
    }

    @Override
    public void validateParameter(HashMap<String, String> map) {
        if (!(map.containsKey("name") && map.containsKey("teamId") && map.containsKey("position")))
            throw new IllegalParameterException(ERR_MSG_ILLEGAL_PARAMETER);
    }
}
