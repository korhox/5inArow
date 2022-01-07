package fi.korho.tictactoe;

import java.io.Console;
import com.github.tomaslanger.chalk.Chalk;

/**
 * Utils class contains several utilities which eases the development
 */
public class Utils {
    private static Console c = System.console();

    /**
     * Asks selected question from the user, and throws an error if given answer is
     * not an integer.
     * 
     * @param question      The question to be asked
     * @return              User's answer
     * @throws Exception    Error message
     */
    public static int askInt(String question) throws Exception {
        try {
            System.out.println(Chalk.on(question).bold());
            System.out.print(Chalk.on("> ").gray());
            return Integer.parseInt(c.readLine());
        } catch (Exception e) {
            throw new Exception("Please give a valid integer");
        }
    }

    /**
     * Clears the console
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
