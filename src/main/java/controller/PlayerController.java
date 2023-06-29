package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.PlayerService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

@Controller
public class PlayerController implements BaseballController {
    private BaseBallService playerService;

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
    @RequestMapping(uri = "선수목록", method = RequestMethod.GET)
    public void select(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        playerService.show(map);
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name") && map.containsKey("teamId") && map.containsKey("position"))
            return true;

        throw new IllegalParameterException("알맞은 파라미터명이 아닙니다.");
    }
}
