package console.CommandMapper;

import exception.IllegalCommandException;

import java.util.HashMap;

public interface CommandMapper {
    void mapCommand(String command, HashMap<String, String> map);
}
