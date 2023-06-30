package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.OutPlayerService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

import static util.messages.ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER;

@Controller
public class OutPlayerController implements BaseballController {

    private final BaseBallService outPlayerService;

    public OutPlayerController() {
        this.outPlayerService = OutPlayerService.getInstance();
    }

    @Override
    @RequestMapping(uri = "퇴출등록", method = RequestMethod.POST)
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        outPlayerService.register(map);
    }

    @Override
    @RequestMapping(uri = "퇴출목록", method = RequestMethod.GET)
    public void select() {
        outPlayerService.show();
    }

    @Override
    public void validateParameter(HashMap<String, String> map) {
        if (!(map.containsKey("playerId") && map.containsKey("reason")))
            throw new IllegalParameterException(ERR_MSG_ILLEGAL_PARAMETER);
    }
}
