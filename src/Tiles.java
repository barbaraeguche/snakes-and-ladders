import java.util.ArrayList;

/**
 * this is the tiles class.
 */
public class Tiles {
    private int tileNumber;
    private final ArrayList<Player> players = new ArrayList<>();

    /**
     * default constructor that initializes the tile-number to 0.
     */
    public Tiles() {
        this.setTileNumber(0);
    }

    /**
     * parameterized constructor that sets the tile-number to the given parameter.
     * @param tileNumber the gameboard squares
     */
    public Tiles(int tileNumber) {
        setTileNumber(tileNumber);
    }

    /**
     * parameterized constructor to set tile-number for each player after every move.
     * @param tileNumber the given tile-number on the gameboard
     * @param players an array of players
     */
    public Tiles(int tileNumber, Player[] players) {
        setTileNumber(tileNumber);
        setPlayers(players);
    }

    /**
     * an accessor method.
     * @return the gameboard tile-number
     */
    public int getTileNumber() {
        return this.tileNumber;
    }

    /**
     * a mutator method.
     * @param players an array of players
     */
    public void setPlayers(Player[] players) {
        for(Player player : players){
            if(player.getPosition() == this.getTileNumber()){
                this.players.add(player);
            }
        }
    }
    /**
     * a mutator method.
     * @param tileNumber the gameboard squares
     */
    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    /**
     * a string method that returns a representation of the gameboard.
     * @return a string representation of a tile object
     */
    @Override
    public String toString() {
        int tileLength = String.valueOf(tileNumber).length();
        String padZeros = tileLength == 1? "00" : tileLength == 2? "0" : "";        

        if(!players.isEmpty()){
            return String.format("\t[[ %s ]]", this.players);
        } else {
            return String.format("\t%s%d", padZeros, tileNumber);
        }
    }
}