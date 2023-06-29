package util;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import lombok.extern.log4j.Log4j2;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.messages.ErrorMessage;
import util.messages.ResponseMessage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
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

    public void consoleMenu() {
        try {
            Set<Class> classes = componentScan("controller");
            while (true) {
                try {
                    log.info(ResponseMessage.SERVICE_ASK);
                    String request = scanner.nextLine();
                    if (request.equals(ResponseMessage.SERVICE_OFF))
                        break;

                    HashMap<String, String> map = requestParser(request);
                    runMatchedMethodIfExists(classes, map.get("command"), map.get("queryString"));
                } catch (IllegalParameterException | IllegalCommandException e) {
                    log.warn(e.getMessage());
                } catch (NoSuchElementException e) {
                    log.warn(ErrorMessage.ERR_MSG_NO_SUCH);
                } catch (InvocationTargetException e) {
                    log.warn(ErrorMessage.ERR_MSG_ILLEGAL_PARAMETER_TYPE);
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

    private static Set<Class> componentScan(String pack) throws URISyntaxException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<Class> classes = new HashSet<>();

        URL packUrl = classLoader.getResource(pack);
        File packDir = new File(packUrl.toURI());
        for (File file : packDir.listFiles()) {
            if (file.getName().endsWith(".class")) {
                String className = pack + '.' + file.getName().replace(".class", "");
                Class cls = Class.forName(className);
                classes.add(cls);
            }
        }
        return classes;
    }

    private static void runMatchedMethodIfExists(Set<Class> classes, String uri, String queryString) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (Class<?> cls : classes) {
            if (cls.isAnnotationPresent(Controller.class)) {
                Method[] methods = cls.getDeclaredMethods();
                for (Method mt : methods) {
                    RequestMapping requestMapping = mt.getDeclaredAnnotation(RequestMapping.class);
                    if (requestMapping != null && uri.equals(requestMapping.uri())) {
                        Object instance = cls.getDeclaredConstructor().newInstance();
                        mt.invoke(instance, queryString);
                        return;
                    }
                }
            }
        }
        log.warn(ErrorMessage.ERR_MSG_ILLEGAL_COMMAND);
    }
}
