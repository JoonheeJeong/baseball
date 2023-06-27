package console.CommandMapper;

import console.BaseBallService;
import exception.IllegalParameterException;

import java.util.HashMap;

public class ShowStadiumCommandMapper implements CommandMapper {
    public static final String name = "야구장목록";
    private final BaseBallService baseBallService;

    public ShowStadiumCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        /*
            TODO : baseBallService.showStadiumList;
        */
    }
}
