package console.CommandMapper;

import exception.IllegalParameterException;
import service.BaseBallService;

import java.util.HashMap;
import java.util.Optional;

public class ShowTeamCommandMapper implements CommandMapper {
    public static final String name = "팀목록";
    private final BaseBallService baseBallService;

    public ShowTeamCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        baseBallService.show(map);
    }
}
