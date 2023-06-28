package console.CommandMapper;

import exception.IllegalParameterException;
import service.BaseBallService;

import java.util.HashMap;
import java.util.Optional;

public class ShowStadiumCommandMapper implements CommandMapper {
    public static final String name = "야구장목록";
    private final BaseBallService baseBallService;

    public ShowStadiumCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        baseBallService.show(map);
    }
}
