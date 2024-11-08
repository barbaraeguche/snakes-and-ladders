
/**
 * this is the player class.
 */
public class Player {
    private String name; 
    private int position, diceValue;

    /**
     * parameterized constructor to initialize player's name, and set position and dice value to 0.
     * @param name the player's name
     */
    public Player(String name) {
        this.setName(name);
        this.setPosition(0);
        this.setDiceValue(0);
    }

    /**
     * an accessor method.
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }
    /**
     * an accessor method.
     * @return the player's position
     */
    public int getPosition() {
        return this.position;
    }
    /**
     * an accessor method.
     * @return the player's dice value
     */
    public int getDiceValue() {
        return this.diceValue;
    }

    /**
     * a mutator method.
     * @param name the player's name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * a mutator method.
     * @param position the player's position
     */
    public void setPosition(int position) {
        this.position = position;
    }
    /**
     * a mutator method.
     * @param diceValue the player's dice value
     */
    public void setDiceValue(int diceValue) {
        this.diceValue = Math.max(diceValue, 0);
    }

    /**
     * a boolean method that returns true if the player has won the game.
     * @return true if the player has reached the goal (position 100), otherwise false
     */
    public boolean hasPlayerWon() {
        return this.getPosition() == 100;
    }

    /**
     * a string method that returns a representation of the player objects.
     * @return a string representation of a player object
     */
    @Override
    public String toString() {
        return name;
    }
}