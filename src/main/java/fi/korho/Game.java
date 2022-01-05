package fi.korho;

import com.github.tomaslanger.chalk.Chalk;

/**
 * Arena class contains the basic tictactoe game logic. Class does not interact
 * with the user.
 */

public class Game {
    private int winRow = 5;
    private int arenaSize = 5;
    private int[][] arena = new int[arenaSize][arenaSize];

    public Game(int size, int winRow) throws Exception {
        if (size < 3 || size > 20) {
            throw new Exception("Arena size must be between 3 and 20");
        }
        this.setWinRow(winRow);
        this.arena = new int[size][size];
    }

    /**
     * Returns an 2d array of arena values
     *
     * @return arena
     */
    public int[][] getArena() {
        return arena;
    }

    /**
     * Prints the player tiles of arena to console
     */
    public void printArena() {
        for (int y = 0; y < this.arena.length; y++) {
            for (int x = 0; x < this.arena[y].length; x++) {

                if (arena[y][x] == 0) {
                    System.out.print(Chalk.on("+ ").gray());
                } else if (arena[y][x] == 1) {
                    System.out.print(Chalk.on("x ").red());
                } else if (arena[y][x] == 2) {
                    System.out.print(Chalk.on("o ").cyan());
                }

            }
            System.err.println();
        }
        System.out.println();
    }

    /**
     * Prints the raw values of arena to console
     */
    public void printArenaRaw() {
        for (int y = 0; y < this.arena.length; y++) {
            for (int x = 0; x < this.arena[y].length; x++) {
                System.out.print(arena[y][x] + " ");
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
        if (this.arenaSize < 5) {
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
     * Sets player's selection to player's tile
     * 
     * @param x
     * @param y
     * @param player
     * 
     * @throws Exception
     */
    public void setTile(int x, int y, int player) throws Exception {
        if (arena[y][x] == 0) {
            arena[y][x] = player;
        } else {
            throw new Exception("That spot is taken!");
        }
    }

    /**
     *  Checks direction for selected player
     * 
     * @param player
     * @param x
     * @param y
     * @param direction_x
     * @param direction_y
     * 
     * @return 0 if no win, 1 if won
     */
    private int checkDirection(int player, int x, int y, int direction_x, int direction_y) {
        try {
            if(this.arena[y + direction_y][x + direction_x] == player){
                return 1 + checkDirection(player, x+direction_x, y+direction_y, direction_x, direction_y);
            }
            return 0;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    /**
     * Checks whether player won last move or not.
     * 
     * @param x
     * @param y
     * @param int Player
     * 
     * @return if player won that round.
     */
    public boolean checkVictory(int x, int y, int player) {
        if(checkDirection(player, x, y, -1, -1) + checkDirection(player, x, y, 1, 1) + 1 >= this.winRow) return(true);
        if(checkDirection(player, x, y, 1, -1) + checkDirection(player, x, y, -1, 1) + 1 >= this.winRow) return(true);
        if(checkDirection(player, x, y, 0, -1) + checkDirection(player, x, y, 0, 1) + 1 >= this.winRow) return(true);
        if(checkDirection(player, x, y, -1, 0) + checkDirection(player, x, y, 1, 0) + 1 >= this.winRow) return(true);

        return false;
    }
}

