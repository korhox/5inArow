package fi.korho;

import com.github.tomaslanger.chalk.Chalk;

public class Main {
    public static void main(String[] args) {
        try {
            Utils.clearScreen();
            int arenaSize = Utils.askInt("How big arena would you like to have?");

            int winRows = 0;
            if (arenaSize >= 5) {
                winRows = Utils.askInt("How many big row wins? (min 5, since you have arena bigger than 5)");
            } else {
                winRows = Utils.askInt("How many big row wins? (min 3)");
            }
            Utils.clearScreen();
            Game arena = new Game(arenaSize, winRows);
            arena.printArena();

            int player = 1;
            boolean gameContinues = true;
            while(gameContinues) {
                if (player == 1) {
                    System.out.print(Chalk.on("x").red() + ": ");
                } else if (player == 2) {
                    System.out.print(Chalk.on("o").cyan() + ": ");
                }
                int x = Utils.askInt("Give the column # of your tile to be added");
                if (player == 1) {
                    System.out.print(Chalk.on("x").red() + ": ");
                } else if (player == 2) {
                    System.out.print(Chalk.on("o").cyan() + ": ");
                }
                int y = Utils.askInt("Give the row # of your tile to be added");
                arena.setTile(x-1, y-1, player);
                Utils.clearScreen();
                arena.printArena();
                if(arena.checkVictory(x, y, player)){
                    if (player == 1) {
                        System.out.println(Chalk.on("Player").red() + " " + Chalk.on("x").red().bold() + " " + Chalk.on("won").red());
                    } else if (player == 2) {
                        System.out.println(Chalk.on("Player").cyan() + " " + Chalk.on("o").red().cyan() + " " + Chalk.on("won").cyan());
                    }
                    gameContinues = false;
                }

                player = (player == 1) ? 2 : 1;
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
