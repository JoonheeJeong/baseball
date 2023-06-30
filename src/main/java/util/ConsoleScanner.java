package util;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import lombok.extern.log4j.Log4j2;
import util.annotation.RequestMapping;
import util.messages.ErrorMessage;
import util.messages.ResponseMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Log4j2
public class ConsoleScanner {
    private static ConsoleScanner consoleInstance;

    private ConsoleScanner() {
    }

    public static ConsoleScanner getConsoleInstance() {
        if (consoleInstance == null)
            consoleInstance = new ConsoleScanner();

        return consoleInstance;
    }

    private final Scanner scanner = new Scanner(System.in);
    private final Assembler assembler = Assembler.getInstance();

    public void consoleMenu() {
        try {
            assembler.componentScan("controller");
            while (true) {
                try {
                    log.info(ResponseMessage.SERVICE_ASK);
                    String request = scanner.nextLine();
                    if (request.equals(ResponseMessage.SERVICE_OFF))
                        break;

                    HashMap<String, String> map = requestParser(request);
                    runMatchedMethodIfExists(map.get("command"), map.get("queryString"));
                } catch (IllegalParameterException | IllegalCommandException e) {
                    log.warn(e.getMessage());
                } catch (NoSuchElementException e) {
                    log.warn(ErrorMessage.ERR_MSG_NO_SUCH);
                } catch (InvocationTargetException e) {
                    log.warn(e.getTargetException());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private HashMap<String, String> requestParser(String request) {
        HashMap<String, String> map = new HashMap<>();

        StringTokenizer commandToken = new StringTokenizer(request, "?");
        map.put("command", commandToken.nextToken());
        if (commandToken.hasMoreTokens())
            map.put("queryString", commandToken.nextToken());

        return map;
    }

    private void runMatchedMethodIfExists(String uri, String queryString) throws IllegalAccessException, InvocationTargetException {
        for (Class<?> cls : assembler.getClasses()) {
            Method[] methods = cls.getDeclaredMethods();
            for (Method mt : methods) {
                RequestMapping requestMapping = mt.getDeclaredAnnotation(RequestMapping.class);
                if (requestMapping != null && requestMapping.uri().equals(uri)) {
                    Object instance = assembler.getInstanceByClass(cls);
                    try {
                        mt.invoke(instance);
                    } catch (IllegalArgumentException e) {
                        mt.invoke(instance, queryString);
                    }
                    return;
                }
            }
        }
        log.warn(ErrorMessage.ERR_MSG_ILLEGAL_COMMAND);
    }
}
