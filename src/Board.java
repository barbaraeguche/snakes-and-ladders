
/**
 * this is the board class.
 */
public class Board {
    //constant variables: gameboard size
    private final int ROWS = 10, COLS = 10;
    private final Tiles[][] gameboard = new Tiles[ROWS][COLS];

    private SnakeLadder snakeLadder;

    /**
     * this method creates the gameboard.
     */
    protected void createBoard() {
        for(int x = 0; x < ROWS; x++) {
            for(int y = 0; y < COLS; y++) {
                //creates a new tile object
                int tileNumber = (x * ROWS) + y + 1;
                gameboard[x][y] = new Tiles(tileNumber);
            }
        }
        snakeLadder.setSnakes();
        snakeLadder.setLadders();
    }

    /**
     * this method makes the gameboard visible.
     */
    protected void showBoard() {
        for(int j = 9; j >= 0; j--) {
            if(j % 2 == 0) {
                for (int x = 0; x < 10; x++) {
                    System.out.print(gameboard[j][x] + "\t");
                }
            } else {
                for (int x = 9; x >= 0; x--) {
                    System.out.print(gameboard[j][x] + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * this method updates the gameboard to reflect each player's position.
     * @param players an array of players
     */
    protected void updateBoard(Player[] players) {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                //creates a new tile object
                int tileNumber = (i * ROWS) + j + 1;
                gameboard[i][j] = new Tiles(tileNumber, players);
            }
        }
        showBoard();
    }

    /**
     * this method moves the player on the gameboard.
     * @param player the current player
     * @param diceRolled the randomly rolled dice value
     */
    protected void movePlayerOnBoard(Player player, int diceRolled) {
        int newPosition = player.getPosition() + diceRolled;

        for(Tiles[] tiles : gameboard) {
            for(Tiles tile : tiles) {
                //check that the player's current position is within the bounds of the
                //gameboard(1 - 100).
                if(newPosition != tile.getTileNumber()) {
                    if(newPosition > 100) {
                        //if the player goes above 100, the position is set back to 100 - extra margin
                        //eg. if position = 103. then the player will be moved back to 97
                        player.setPosition(100 - (newPosition - 100));
                    }
                }

                /*
                 * ////this checks if the player's current position is within the bounds of the
                 * gameboard(1 - 100).
                 * //if (newPosition == tile.getTileNumber()) {
                 */

                //set the player's position to the current tile's position
                player.setPosition(tile.getTileNumber());

                //at tile 80, the player takes the ladder that goes up to tile 100.
                if(player.getPosition() == 80 || player.hasPlayerWon()) {
                    System.out.printf("%s rolled a dice-value of %d, and is now at tile %d%n", player, player.getDiceValue(), player.getPosition());
                    snakeLadder.checkForSnakesOrLadders();

                    //print game over message and set the game over flag to true
                    System.out.printf("%s landed on tile %d, and has WON THE GAME!!!%n", player, player.getPosition());
                    updateBoard(snakeLadder.players);
                    snakeLadder.isGameOver = true;

                    //print the closing message and end the game
                    System.out.print("\nThank you for playing Snakes & Ladders, goodbye for now...");
                    System.exit(0);
                }

                //}
            }
        }
    }
}