package fi.korho.tictactoe;

import com.github.tomaslanger.chalk.Chalk;

/**
 * Arena class contains the basic 5inArow game logic.
 */

public class Game {
    private int winRow = 5;
    private int[][] arena = new int[5][5];

    public Game(int size, int winRow) {
        this.winRow = winRow;
        this.arena = new int[size][size];
    }

    /**
     * Returns an 2d array of arena values
     *
     * @return arena
     */
    public int[][] getArena() {
        return this.arena;
    }

    /**
     * Prints the tiles of the arena to console
     */
    public void printArena() {
        if(this.arena.length >= 5){
            System.out.print("   ");
            for (int i = 0; i < this.arena.length; i++) {
                System.out.print(Chalk.on(((i + 1) >= 10 ? ((i + 1) >= 20 ? 2 : 1) : " ") + " ").white());
            }
            System.out.println("");

            System.out.print("   ");
            for (int i = 0; i < this.arena.length; i++) {
                System.out.print(Chalk.on(((i + 1) >= 10 ? ((i + 1) >= 20 ? i - 19 : i - 9) : i + 1) + " ").white());
            }
            System.out.println("");
        }
        
        for (int y = 0; y < this.arena.length; y++) {
            if(this.arena.length >= 5) System.out.print(Chalk.on(((y + 1 >= 10) ? "" : " ") + (y + 1 ) + " ").white());

            for (int x = 0; x < this.arena[y].length; x++) {
                if (this.arena[y][x] == 0) {
                    System.out.print(Chalk.on("+ ").gray());
                } else if (this.arena[y][x] == 1) {
                    System.out.print(Chalk.on("x ").red());
                } else if (this.arena[y][x] == 2) {
                    System.out.print(Chalk.on("o ").cyan());
                }

            }
            System.err.println();
        }
        System.out.println();
    }
    /**
     * Sets the winRow value
     *
     * @param int newWinRow
     */
    public void setWinRow(int newWinRow) throws Exception {
        if (this.arena.length < 5) {
            if (newWinRow < 3) {
                throw new Exception("Win round must be at least 3 when arena size is less than 5.");
            }
        } else {
            if (newWinRow > 5) {
                throw new Exception("Win round must be at least 5 when arena size is more than 5.");
            }
        }
        this.winRow = newWinRow;
    }

    /**
     * Returns the winRow value
     *
     * @return the winRow variable as integer
     */
    public int getWinRow() {
        return this.winRow;
    }

    /**
     * Sets player's move to the arena
     * 
     * @param x
     * @param y
     * @param player
     * 
     * @throws Exception
     */
    public void setTile(int x, int y, int player) throws Exception {
        if (this.arena[y][x] == 0) {
            this.arena[y][x] = player;
        } else {
            throw new Exception("That spot is taken!");
        }
    }

    /**
     * Checks given direction for players tile 
     * 
     * @param player
     * @param x
     * @param y
     * @param direction_x
     * @param direction_y
     * 
     * @return 0 if no win, 1 + checks next tile if won.
     */
    private int checkDirection(int player, int x, int y, int direction_x, int direction_y) {
        try {
            if (this.arena[y + direction_y][x + direction_x] == player) {
                return 1 + checkDirection(player, x + direction_x, y + direction_y, direction_x, direction_y);
            }
            return 0;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    /**
     * Checks whether player won last move or not in given arena.
     * 
     * @param x
     * @param y
     * @param int Player
     * 
     * @return if player won that round.
     */
    public boolean checkVictory(int x, int y, int player) {
        if (checkDirection(player, x, y, -1, -1) + checkDirection(player, x, y, 1, 1) + 1 >= this.winRow)
            return (true);
        if (checkDirection(player, x, y, 1, -1) + checkDirection(player, x, y, -1, 1) + 1 >= this.winRow)
            return (true);
        if (checkDirection(player, x, y, 0, -1) + checkDirection(player, x, y, 0, 1) + 1 >= this.winRow)
            return (true);
        if (checkDirection(player, x, y, -1, 0) + checkDirection(player, x, y, 1, 0) + 1 >= this.winRow)
            return (true);

        return false;
    }
}
