package fi.korho;

import java.io.Console;
import com.github.tomaslanger.chalk.Chalk;

public class Utils {
    private static Console c = System.console();

    public static int askInt(String question) throws Exception {
        try {
            System.out.println(Chalk.on(question).bold());
            System.out.print(Chalk.on("> ").gray());
            return Integer.parseInt(c.readLine());
        } catch (Exception e) {
            return 0;
        }
    }
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
