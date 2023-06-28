package console.CommandMapper;

import exception.IllegalParameterException;
import service.BaseBallService;

import java.util.HashMap;
import java.util.Optional;

public class ShowPlayerCommandMapper implements CommandMapper {
    public static final String name = "선수목록";
    private final BaseBallService baseBallService;

    public ShowPlayerCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        if (map.containsKey("teamId")) {
            baseBallService.show(map);
        } else {
            throw new IllegalParameterException("알맞은 파라미터 형식이 아닙니다.");
        }
    }
}
