package console;

import java.util.Map;

public interface CommandMapper {
    void mapCommand(String command, Map<String, String> map);
}
