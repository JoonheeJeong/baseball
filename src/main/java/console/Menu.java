package console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 컨트롤러 역할을 대신하므로,
 * service를 주입받아서
 * 메소드 사용
 */
public class Menu {
    private static Menu instance;
    private final Map<String, CommandMapper> commandMapperMap;
    private final BaseBallService baseballService = new BaseBallService();

    private final Scanner scanner = new Scanner(System.in);
    private final String[] commandList = {"야구장등록", "야구장목록", "팀등록", "팀목록", "선수등록", "선수목록", "퇴출등록", "퇴출목록", "포지션별목록"};

    private Menu() {
        this.commandMapperMap = new HashMap<>();
        commandMapperMap.put("야구장등록", new RegisterStadiumCommandMapper(baseballService));
        commandMapperMap.put("팀등록", new RegisterTeamCommandMapper(baseballService));
        commandMapperMap.put("선수등록", new RegisterPlayerCommandMapper(baseballService));
        commandMapperMap.put("퇴출등록", new RegisterOutPlayerCommandMapper(baseballService));
        commandMapperMap.put("야구장목록", new ShowStadiumCommandMapper(baseballService));
        commandMapperMap.put("팀목록", new ShowTeamCommandMapper(baseballService));
        commandMapperMap.put("선수목록", new ShowPlayerCommandMapper(baseballService));
        commandMapperMap.put("퇴출목록", new ShowOutPlayerCommandMapper(baseballService));
        commandMapperMap.put("포지션별목록", new ShowPositionPlayerCommandMapper(baseballService));
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private void parser(String request) throws IllegalArgumentException{
        StringTokenizer getCommandToken = new StringTokenizer(request, "?");
        String command = getCommandToken.nextToken();
        validateCommand(command);
        HashMap<String, String> map = new HashMap<>();
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
        if (commandMapper != null) {
            commandMapper.mapCommand(command, map);
        } else {
            throw new NullPointerException();
        }
    }

    private boolean validateCommand(String command) {
        for (int i = 0; i < commandList.length; i++) {
            if (command.equals(commandList[i])) return true;
        }
        //커스텀 익셉션
        throw new IllegalArgumentException();
    }

    public void consoleMenu() {
        while (true) {
            try {
                System.out.println("===============================");
                for (String s : commandList) {
                    System.out.println("- " + s);
                }
                System.out.println("===============================");
                System.out.println("어떤 기능을 요청하시겠습니까?");

                String request = scanner.nextLine();
                parser(request);

                if (scanner.equals("종료")) break;
            } catch (IllegalArgumentException e) {
                System.err.println("잘못된 기능의 이름입니다.");
            }
        }
    }
}
