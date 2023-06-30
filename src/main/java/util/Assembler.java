package util;

import util.annotation.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Assembler {

    private static final Assembler INSTANCE = new Assembler();

    private Map<Class<?>, Object> componentMap;

    private Assembler() {
        componentMap = new HashMap<>();
    }

    public static Assembler getInstance() {
        return INSTANCE;
    }

    public void componentScan(final String pkg) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL pkgUrl = classLoader.getResource(pkg);
        File pkgDir = new File(pkgUrl.toURI());
        for (File file : Objects.requireNonNull(pkgDir.listFiles())) {
            if (file.getName().endsWith(".class")) {
                String className = pkg + '.' + file.getName().replace(".class", "");
                Class<?> cls = Class.forName(className);
                for (Annotation anno : cls.getAnnotations())
                    if (anno.annotationType() == Component.class || anno.annotationType().isAnnotationPresent(Component.class))
                        componentMap.put(cls, cls.getDeclaredConstructor().newInstance());
            }
        }
    }

    public Set<Class<?>> getClasses() {
        return componentMap.keySet();
    }

    public Object getInstanceByClass(Class<?> cls) {
        return componentMap.get(cls);
    }
}
