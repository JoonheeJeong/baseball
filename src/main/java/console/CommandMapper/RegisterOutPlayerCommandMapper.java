package console.CommandMapper;

import exception.IllegalParameterException;
import service.BaseBallService;

import java.util.HashMap;

public class RegisterOutPlayerCommandMapper implements CommandMapper {
    public static final String name = "퇴출등록";
    private final BaseBallService baseBallService;

    public RegisterOutPlayerCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        if (map.containsKey("playerId") && map.containsKey("reason")) {
            /*
            TODO : baseBallService.registerOutPlayer(map);
            */
        } else {
            throw new IllegalParameterException("알맞은 파라미터 형식이 아닙니다.");
        }
    }

}
