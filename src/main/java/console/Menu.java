package console;

import console.CommandMapper.*;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import service.StadiumService;
import service.TeamService;

import java.util.*;

/**
 * 컨트롤러 역할을 대신하므로,
 * service를 주입받아서
 * 메소드 사용
 */
public class Menu {
    private static Menu instance;
    private service.BaseBallService baseBallService;
    private final Map<String, CommandMapper> commandMapperMap;
    private static HashMap<String, String> map = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private Menu() {
        this.commandMapperMap = new HashMap<>();
        commandMapperMap.put(RegisterStadiumCommandMapper.name, new RegisterStadiumCommandMapper(baseBallService = new StadiumService()));
        commandMapperMap.put(RegisterTeamCommandMapper.name, new RegisterTeamCommandMapper(baseBallService = new TeamService()));
        commandMapperMap.put(RegisterPlayerCommandMapper.name, new RegisterPlayerCommandMapper(baseBallService));
        commandMapperMap.put(RegisterOutPlayerCommandMapper.name, new RegisterOutPlayerCommandMapper(baseBallService));
        commandMapperMap.put(ShowStadiumCommandMapper.name, new ShowStadiumCommandMapper(baseBallService = new StadiumService()));
        commandMapperMap.put(ShowTeamCommandMapper.name, new ShowTeamCommandMapper(baseBallService = new TeamService()));
        commandMapperMap.put(ShowPlayerCommandMapper.name, new ShowPlayerCommandMapper(baseBallService));
        commandMapperMap.put(ShowOutPlayerCommandMapper.name, new ShowOutPlayerCommandMapper(baseBallService));
        commandMapperMap.put(ShowPositionPlayerCommandMapper.name, new ShowPositionPlayerCommandMapper(baseBallService));
    }

    private void parser(String request) throws IllegalParameterException, IllegalCommandException {
        map.clear();
        StringTokenizer getCommandToken = new StringTokenizer(request, "?");
        String command = getCommandToken.nextToken();
        validateCommand(command);

        if (getCommandToken.hasMoreTokens()) {
            String parameterSet = getCommandToken.nextToken();
            StringTokenizer getParameterToken = new StringTokenizer(parameterSet, "&");
            while (getParameterToken.hasMoreTokens()) {
                String parameter = getParameterToken.nextToken();
                StringTokenizer getMapToken = new StringTokenizer(parameter, "=");
                String key = getMapToken.nextToken();
                String value = getMapToken.nextToken();
                map.put(key, value);
            }
        }

        CommandMapper commandMapper = commandMapperMap.get(command);
        commandMapper.mapCommand(command, map);
    }

    private boolean validateCommand(String command) throws IllegalCommandException{
        if (!commandMapperMap.containsKey(command)) {
            throw new IllegalCommandException("알맞은 요청형식이 아닙니다.");
        }
        return true;
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void consoleMenu() {
        while (true) {
            try {
                System.out.println("어떤 기능을 요청하시겠습니까?");
                String request = scanner.nextLine();
                parser(request);

                if (scanner.equals("종료")) break;
            } catch (IllegalParameterException | IllegalCommandException e) {
                System.err.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.err.println("입력되지 않은 값이 있습니다.");
            }
        }
    }
}
