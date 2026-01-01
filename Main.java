
import global.Globals;
import menus.MainMenu;

public class Main {

    public static void main(String[] args) {
        // O(n)
        while (!Globals.exitFromSoft) {
            MainMenu.mainManu();
        }
    }
}
