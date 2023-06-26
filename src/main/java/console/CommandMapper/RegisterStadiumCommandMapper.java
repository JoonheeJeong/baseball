package console.CommandMapper;

import console.BaseBallService;
import exception.IllegalParameterException;

import java.util.HashMap;

public class RegisterStadiumCommandMapper implements CommandMapper {
    public static final String name = "야구장등록";
    private final BaseBallService baseBallService;

    public RegisterStadiumCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        if (map.containsKey("name")) {
            /*
            TODO : baseBallService.registerStadium(map);
            */
        } else {
            throw new IllegalParameterException("알맞은 파라미터 형식이 아닙니다.");
        }
    }
}
