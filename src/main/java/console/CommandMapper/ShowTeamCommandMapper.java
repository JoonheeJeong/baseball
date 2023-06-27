package console.CommandMapper;

import console.BaseBallService;
import exception.IllegalParameterException;

import java.util.HashMap;

public class ShowTeamCommandMapper implements CommandMapper {
    public static final String name = "팀목록";
    private final BaseBallService baseBallService;

    public ShowTeamCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        /*
            TODO : baseBallService.showTeamList();
        */
    }
}
