package console;

import java.util.Map;

public class ShowPositionPlayerCommandMapper implements CommandMapper{
    private final BaseBallService baseBallService;

    public RegisterOutPlayerCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, Map<String, String> map) {
        /*
            TODO : baseBallService.PositionPlayerList();
        */
    }
}
