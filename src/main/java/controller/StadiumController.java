package controller;

import exception.IllegalParameterException;
import service.BaseBallService;
import service.StadiumService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.RequestMethod;

import java.util.HashMap;

import static util.messages.ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER;

@Controller
public class StadiumController implements BaseballController {
    private final BaseBallService stadiumService;

    public StadiumController() {
        this.stadiumService = StadiumService.getInstance();
    }

    @Override
    @RequestMapping(uri = "야구장등록", method = RequestMethod.POST)
    public void add(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        stadiumService.register(map);
    }

    @Override
    @RequestMapping(uri = "야구장목록", method = RequestMethod.GET)
    public void showList() {
        stadiumService.show();
    }

    @Override
    public void validateParameter(HashMap<String, String> map) {
        if (!(map.containsKey("name")))
            throw new IllegalParameterException(ERR_MSG_ILLEGAL_PARAMETER);
    }
}