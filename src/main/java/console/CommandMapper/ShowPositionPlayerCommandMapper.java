package console.CommandMapper;

import exception.IllegalParameterException;
import service.BaseBallService;

import java.util.HashMap;

public class ShowPositionPlayerCommandMapper implements CommandMapper {
    public static final String name = "포지션별목록";
    private final BaseBallService baseBallService;

    public ShowPositionPlayerCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        /*
            TODO : baseBallService.PositionPlayerList();
        */
    }
}
