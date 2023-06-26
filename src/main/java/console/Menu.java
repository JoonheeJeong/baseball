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

    private final Scanner scanner = new Scanner(System.in);
    private final String[] commandList = {"야구장등록", "야구장목록", "팀등록", "팀목록", "선수등록", "선수목록", "퇴출등록", "퇴출목록", "포지션별목록"};

    private Menu() {

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
        mapper(command, map);
    }

    private void mapper(String command, Map<String, String> map) {
        if (command.equals(commandList[0])) {
            /*
            TODO : baseBallService.registerStadium(map);
             */
            System.out.println(command + "\n" + map);
        } else if (command.equals(commandList[1])) {
            /*
            TODO : baseBallService.showStadiumList;
             */
            System.out.println(command);
        } else if (command.equals(commandList[2])) {
            /*
            TODO : baseBallService.registerTeam(map);
             */
            System.out.println(command + "\n" + map);
        } else if (command.equals(commandList[3])) {
            /*
            TODO : baseBallService.showTeamList();
             */
            System.out.println(command);
        } else if (command.equals(commandList[4])) {
            /*
            TODO : baseBallService.registerPlayer(map);
             */
            System.out.println(command + "\n" + map);
        } else if (command.equals(commandList[5])) {
            /*
            TODO : baseBallService.showPlayerList(map);
             */
            System.out.println(command + "\n" + map);
        } else if (command.equals(commandList[6])) {
            /*
            TODO : baseBallService.registerOutPlayer(map);
             */
            System.out.println(command + "\n" + map);
        } else if (command.equals(commandList[7])) {
            /*
            TODO : baseBallService.showOutPlayerList();
             */
            System.out.println(command);
        } else if (command.equals(commandList[8])) {
            /*
            TODO : baseBallService.PositionPlayerList();
             */
            System.out.println(command);
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
