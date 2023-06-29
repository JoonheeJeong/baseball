package controller;

import exception.IllegalParameterException;
import exception.IllegalRequestTypeException;
import service.BaseBallService;
import service.StadiumService;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.messages.ErrorMessage;

import java.util.HashMap;

@Controller
public class StadiumController implements BaseballController {
    private BaseBallService stadiumService;

    public StadiumController() {
        this.stadiumService = StadiumService.getInstance();
    }

    @Override
    @RequestMapping(uri = "야구장등록")
    public void insert(String queryString) {
        HashMap<String, String> map = parameterParser(queryString);
        validateParameter(map);
        stadiumService.register(map);
    }

    @Override
    @RequestMapping(uri = "야구장목록")
    public void select(String queryString) {
        if (queryString != null) {
            throw new IllegalRequestTypeException(ErrorMessage.ERR_MSG_ILLEGAL_REQUEST_TYPE);
        }
        HashMap<String, String> map = null;
        stadiumService.show(map);
    }

    @Override
    public boolean validateParameter(HashMap<String, String> map) {
        if (map.containsKey("name") && map.size() == 1) {
            return true;
        }
        throw new IllegalParameterException(ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER);
    }


}