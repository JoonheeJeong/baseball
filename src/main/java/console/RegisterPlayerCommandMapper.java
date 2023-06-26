package console;

import java.util.Map;

public class RegisterPlayerCommandMapper implements CommandMapper{
    private final BaseBallService baseBallService;

    public RegisterPlayerCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, Map<String, String> map) {
        //파라미터 검증, TODO : 커스텀 예외로 대체하기
        if (!map.containsKey("name") && !map.containsKey("teamId") && !map.containsKey("position")) {
            throw new NullPointerException();
        }
        /*
            TODO : baseBallService.registerTeam(map);
        */
    }
}
