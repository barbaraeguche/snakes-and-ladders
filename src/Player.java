
/**
 * This is the Player Class for each player in the LadderandSnake Class. It implements methods for naming the player,
 * setting the player's position, and setting the dicevalue for each player.
 */
public class Player {
    private String name; private int position, diceValue;

    /**
     * This is the default constructor
     */
    public Player() {
        this.setName("");
        this.setPosition(0);
        this.setDiceValue(0);
    }

    /**
     * This constructor sets the player's names
     * @param name to set the name of players
     */
    public Player(String name) {
        this.setName(name);
        this.setPosition(0);
        this.setDiceValue(0);
    }

    /**
     * An accessor method to get the name of the player
     * @return name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * A mutator method to set the name of the player
     * @param name name of the player to be passed
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * An accessor method to get the position of the player
     * @return position of the player
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * A mutator method to set the position of the player
     * @param position position of the player to be passed
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * A boolean method that returns true if the player has won the game
     * @return position of the player at square 100 on the gameboard
     */
    public boolean hasPlayerWon() {
        return this.getPosition() == 100;
    }

    /**
     * An accessor method to get the diceValue of the player
     * @return the value of the randomly flipped die
     */
    public int getDiceValue() {
        return this.diceValue;
    }

    /**
     * A mutator method to set the diceValue of the player. It sets the diceValue to the maximum of either the int diceValue passed or zero
     * @param diceValue the value of the randomly flipped die to be passed
     */
    public void setDiceValue(int diceValue) {
        this.diceValue = Math.max(diceValue, 0);
    }

    /**
     * A String method that returns a String representation of the players
     * @return name of the player as a String
     */
    @Override
    public String toString() {
        return name;
    }
}