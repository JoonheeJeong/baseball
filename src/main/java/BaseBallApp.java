import console.Menu;

public class BaseBallApp {
    private static BaseBallApp baseBallApp;
    private final Menu menu = Menu.getInstance();

    public static BaseBallApp getInstance() {
        if (baseBallApp == null) {
            baseBallApp = new BaseBallApp();
        }
        return baseBallApp;
    }

    private BaseBallApp() {

    }

    public void run() {
        menu.consoleMenu();
    }
}
