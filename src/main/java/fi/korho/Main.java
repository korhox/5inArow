package fi.korho;

import com.github.tomaslanger.chalk.Chalk;

public class Main {
    public static void main(String[] args) {

        Utils.clearScreen();

        // Request arena size from the player and make sure it's between 3 and 20
        int arenaSize = 0;
        while (arenaSize < 3 || arenaSize > 20) {
            try {
                arenaSize = Utils.askInt("How big arena would you like to have?");
                if (arenaSize < 3 || arenaSize > 20) {
                    throw new Exception("Plese give an integer between 3 and 20");
                }
            } catch (Exception e) {
                System.out.println(Chalk.on(e.getMessage()).red());
            }
        }

        // Request the length of the required streak for a victory and make sure it is
        // between 3 and the size of arena if the arena size is smaller than 10
        // and between 5 and the size of the arena otherwise.
        int winRows = 0;
        while (arenaSize < 10 && (winRows > arenaSize || winRows < 3) || arenaSize >= 10
                && (winRows > arenaSize || winRows < 5)) {
            try {
                if (arenaSize >= 10) {
                    winRows = Utils.askInt(
                            "How big streak is required for a victory? (min 5, since you have arena bigger than 10)");
                    if (winRows > arenaSize || winRows < 5)
                        throw new Exception("Please enter an integer between 5 and " + arenaSize);
                } else {
                    winRows = Utils.askInt("How big streak is required for a victory? (min 3)");
                    if (winRows > arenaSize || winRows < 3)
                        throw new Exception("Please enter an integer between 3 and " + arenaSize);
                }
            } catch (Exception e) {
                System.out.println(Chalk.on(e.getMessage()).red());
            }
        }

        // Request the number of playe0  (1 or 2)
        int players = 0;
        while (players != 1 && players != 2) {
            try {
                players = Utils.askInt("How many players are playing? (1 or 2)");
                if (players != 1 && players != 2) {
                    throw new Exception("Plese enter 1 or 2");
                }
            } catch (Exception e) {
                System.out.println(Chalk.on(e.getMessage()).red());
            }
        }

        String mode = "easy";

        // Create game
        Game arena = new Game(arenaSize, winRows);

        // Print game
        Utils.clearScreen();
        arena.printArena();

        String tiles = "";

        for (int y = 0; y < arenaSize; y++) {
            for (int x = 0; x < arenaSize; x++) {
                tiles += " " + y + "," + x;
            }
        }
        tiles = tiles.replaceFirst(" ", "");

        int player = 1;
        boolean gameContinues = true;
        while (gameContinues) {
            int x = 0;
            int y = 0;

            if(player == 1){
                System.out.print(Chalk.on("x").red() + ": ");
                while (x < 1 || x > arenaSize) {
                    try {
                        x = Utils.askInt("Give the column # for the tile to be added");
                        if (x < 1 || x > arenaSize) throw new Exception("Column number needs to be between 1 and " + arenaSize);
                    } catch (Exception e) {
                        System.out.println(Chalk.on(e.getMessage()).red());
                    }
                }

                System.out.print(Chalk.on("x").red() + ": ");
                while (y < 1 || y > arenaSize) {
                    try {
                        y = Utils.askInt("Give the row # for the tile to be added");
                        if (y < 1 || y > arenaSize)
                            throw new Exception("Row number needs to be between 1 and " + arenaSize);
                    } catch (Exception e) {
                        System.out.println(Chalk.on(e.getMessage()).red());
                    }
                }
            }

            if(players == 2 && player == 2){
                System.out.print(Chalk.on("o").cyan() + ": ");
                while (x < 1 || x > arenaSize) {
                    try {
                        x = Utils.askInt("Give the column # for the tile to be added");
                        if (x < 1 || x > arenaSize)
                            throw new Exception("Column number needs to be between 1 and " + arenaSize);
                    } catch (Exception e) {
                        System.out.println(Chalk.on(e.getMessage()).red());
                    }
                }

                System.out.print(Chalk.on("o").cyan() + ": ");
                while (y < 1 || y > arenaSize) {
                    try {
                        y = Utils.askInt("Give the row # for the tile to be added");
                        if (y < 1 || y > arenaSize)
                            throw new Exception("Row number needs to be between 1 and " + arenaSize);
                    } catch (Exception e) {
                        System.out.println(Chalk.on(e.getMessage()).red());
                    }
                }

            } 
            if(players == 1 && player == 2){
                //Easy mode means that the "AI" generates random number from free tiles
                if(mode.equals("easy")){
                    int index = (int) Math.random() * tiles.split(" ").length;
                    String tile = tiles.split(" ")[index];
                    x = Integer.parseInt(tile.split(",")[0]) + 1;
                    y = Integer.parseInt(tile.split(",")[1]) + 1;
                }
                // Hard mode means that the 
                
            }
            
            //Delete current tile from the tiles string
            String delete_tile = (x - 1) + "," + (y - 1);
            tiles = tiles.replace(delete_tile + " ", "");
            tiles = tiles.replace(" " + delete_tile, "");
            tiles = tiles.replace(delete_tile, "");

            try{
                arena.setTile(x - 1, y - 1, player);
                Utils.clearScreen();
                arena.printArena();
                if (arena.checkVictory(x - 1, y - 1, player)) {
                    if (player == 1) {
                        System.out.println(Chalk.on("Player").red() + " " + Chalk.on("x").red().bold() + " "
                                + Chalk.on("won").red());
                    } else if (player == 2) {
                        System.out.println(Chalk.on("Player").cyan() + " " + Chalk.on("o").red().bold() + " "
                                + Chalk.on("won").cyan());
                    }
                    gameContinues = false;
                } else if(tiles.length() == 0){
                    System.out.println(Chalk.on("It's a tie!!").bold().white() + "" + Chalk.on("(no need to fight :3)").gray());
                    gameContinues = false;
                }

                player = (player == 1) ? 2 : 1;
            }catch(Exception e){
                System.out.println(Chalk.on(e.getMessage()).red());
            }
        }
    }
}