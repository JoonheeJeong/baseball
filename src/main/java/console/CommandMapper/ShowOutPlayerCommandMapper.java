package console.CommandMapper;

import console.BaseBallService;
import exception.IllegalParameterException;

import java.util.HashMap;

public class ShowOutPlayerCommandMapper implements CommandMapper {
    public static final String name = "퇴출목록";
    private final BaseBallService baseBallService;

    public ShowOutPlayerCommandMapper(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    @Override
    public void mapCommand(String command, HashMap<String, String> map) throws IllegalParameterException {
        /*
            TODO : baseBallService.showOutPlayerList();
        */
    }
}
