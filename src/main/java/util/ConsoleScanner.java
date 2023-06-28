package util;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import lombok.extern.log4j.Log4j2;
import util.annotation.Controller;
import util.annotation.RequestMapping;
import util.annotation.Service;

import java.io.File;
import java.lang.annotation.Annotation;
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
        if (consoleInstance == null) {
            consoleInstance = new ConsoleScanner();
        }
        return consoleInstance;
    }

    private Scanner scanner = new Scanner(System.in);

    public void consoleMenu() {
        try {
            Set<Class> classes = componentScan("controller");
            while (true) {
                System.out.println("어떤 기능을 요청하시겠습니까?");
                String request = scanner.nextLine();
                HashMap<String, String> map = new HashMap<>();
                map = CommandParser(request);

                findUri(classes, map.get("command"), map.get("queryString"));

                if (scanner.equals("종료")) break;
            }
        } catch (IllegalParameterException | IllegalCommandException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("입력되지 않은 값이 있습니다.");
        } catch (Exception e) {
            System.err.println("컴포넌트 스캔 오류");
        }
    }

    private HashMap<String, String> CommandParser(String request) {
        HashMap<String, String> map = new HashMap<>();

        StringTokenizer commandToken = new StringTokenizer(request, "?");
        map.put("command", commandToken.nextToken());
        if (commandToken.hasMoreTokens()) {
            map.put("queryString", commandToken.nextToken());
        }
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

    private static void findUri(Set<Class> classes, String uri, String queryString) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        boolean isFind = false;
        for (Class<?> cls : classes) {
            if (cls.isAnnotationPresent(Controller.class)) {
                Object instance = cls.getDeclaredConstructor().newInstance();
                Method[] methods = cls.getDeclaredMethods();

                for (Method mt : methods) {
                    Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
                    if (anno instanceof RequestMapping) {
                        RequestMapping requestMapping = (RequestMapping) anno;
                        if (requestMapping.uri().equals(uri)) {
                            isFind = true;
                            mt.invoke(instance, queryString);
                        }
                    }
                }
            }
        }
        if (isFind == false) {
            log.info("Invalid Approach");
        }
    }

    private static Object dependencyInjection(Set<Class> classes, Class cls) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String controller = cls.getName().replaceAll("controller.", "").replaceAll("Controller", "");
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Service.class)) {
                String service = clazz.getName().replaceAll("service", "").replaceAll("Service", "");
                if (controller.equals(service)) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    return instance;
                }
            }
        }
        return null;
    }
}
