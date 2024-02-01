import java.util.ArrayList;

/**
 * This is the Tiles Class for each tile/square on the gameboard in the LadderandSnake Class. It implements methods for the tile-number/squares of the gameboard,
 * and setting the player's position to the exact tile-number after each flipped die move.
 */
public class Tiles {
    private int tileNumber;
    private final ArrayList<Player> players = new ArrayList<>();  //an arraylist of both of the players in the game.

    /**
     * This is the default constructor
     */
    public Tiles() {
        this.setTileNumber(0);
    }

    /**
     * Constructor with an int parameter set the tile-number/squares of the gameboard
     * @param tileNumber to set the gameboard squares
     */
    public Tiles(int tileNumber) {
        setTileNumber(tileNumber);
    }

    /**
     * Constructor with an int and Player array parameter to set each player to the exact tile-number/square after each move
     * @param tileNumber to set each player to it's corresponding gameboard square
     * @param players an array of players that adds each player's position to the gameboard to it's corresponding tile-number/square
     */
    public Tiles(int tileNumber, Player[] players) {
        setTileNumber(tileNumber);
        setPlayers(players);
    }

    /**
     * A mutator method to set each player in the Player array to it's corresponding tile-number/square
     * @param players an array of players that adds each player's position to the gameboard
     */
    public void setPlayers(Player[] players) {
        for (Player p : players){
            if (p.getPosition() == this.getTileNumber()){
                this.players.add(p);
            }
        }
    }

    /**
     * An accessor method to get the tile-number/square of the gameboard
     * @return tile-number of the gameboard
     */
    public int getTileNumber() {
        return this.tileNumber;
    }

    /**
     * A mutator method to set the tile-number/square of the gameboard
     * @param tileNumber tile-number/square on the gameboard to passed
     */
    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    /**
     * A String method that returns a String representation of the gameboard
     * @return the gameboard tile-number/squares and positions of the players on the gameboard as a String
     */
    @Override
    public String toString() {
        return "\t\t" + this.tileNumber + " <" + this.players + ">";
    }
}
