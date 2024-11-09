import java.util.Random;
import java.util.Scanner;

/**
 * this is the snakeladder class. this class is responsible for creating,
 * showing and updating the gameboard, moving the players on the gameboard,
 * setting up the snakes and ladders on the gameboard, running the game, and
 * handling player turns.
 */
public class SnakeLadder {
    protected Player[] players = new Player[2];
    private Player playerOne, playerTwo;

    private Board board;
    protected boolean isGameOver = false;

    //constant variables: number of snakes and ladders
    private final int NUM_SNAKES = 8, NUM_LADDERS = 9;
    private int[][] snakes, ladders;

    /**
     * default constructor that initializes players.
     */
    public SnakeLadder() {
        Scanner scanner = new Scanner(System.in);
            //creating a new player object for each player in the players array.
            for(int i = 0; i < players.length; i++) {
                System.out.printf("\nEnter a single name for player %d: ", i);
                
                //take user input
                String player = scanner.next();
                players[i] = new Player(player);
            }

            //this is creating and showing the gameboard.
            board.createBoard();
            board.showBoard();
        scanner.close();
    }

    /**
     * this method generates random dice values for each player.
     * 
     * @return a random number between 1 and 6 inclusively
     */
    public static int flipDice() {
        return (new Random()).nextInt(6) + 1;
    }
    // ----------------------------------------------------------------- //


    // ----------------------------------------------------------------- //
    /**
     * this method sets the snakes on the gameboard.
     */
    protected void setSnakes() {
        snakes = new int[NUM_SNAKES][2];

        snakes[0][0] = 16;
        snakes[0][1] = 6;
        snakes[1][0] = 48;
        snakes[1][1] = 30;
        snakes[2][0] = 64;
        snakes[2][1] = 60;
        snakes[3][0] = 79;
        snakes[3][1] = 19;
        snakes[4][0] = 93;
        snakes[4][1] = 68;
        snakes[5][0] = 95;
        snakes[5][1] = 24;
        snakes[6][0] = 97;
        snakes[6][1] = 76;
        snakes[7][0] = 98;
        snakes[7][1] = 78;
    }

    /**
     * this method sets the ladders on the gameboard.
     */
    protected void setLadders() {
        ladders = new int[NUM_LADDERS][2];

        ladders[0][0] = 1;
        ladders[0][1] = 38;
        ladders[1][0] = 4;
        ladders[1][1] = 14;
        ladders[2][0] = 9;
        ladders[2][1] = 31;
        ladders[3][0] = 21;
        ladders[3][1] = 42;
        ladders[4][0] = 28;
        ladders[4][1] = 84;
        ladders[5][0] = 36;
        ladders[5][1] = 44;
        ladders[6][0] = 51;
        ladders[6][1] = 67;
        ladders[7][0] = 71;
        ladders[7][1] = 91;
        ladders[8][0] = 80;
        ladders[8][1] = 100;
    }
    // ----------------------------------------------------------------- //


    // ----------------------------------------------------------------- //
    /**
     * this method takes a player down a snake, and updates the player's position.
     * 
     * @param player the current player
     * @param i      the index of the snake in the snakes array
     */
    private void playerAtSnake(Player player, int i) {
        player.setPosition(snakes[i][1]);
        System.out.printf("Uh oh. Snake takes %s from tile %d to tile %d%n", player, snakes[i][0], snakes[i][1]);
        System.out.printf("%s is now on tile %d%n", player, player.getPosition());
    }
    /**
     * this method takes a player up a ladder, and updates the player's position.
     * 
     * @param player the current player
     * @param i      the index of the ladder in the ladders array
     */
    private void playerOnLadder(Player player, int i) {
        player.setPosition(ladders[i][1]);
        System.out.printf("Yay!! Ladder takes %s from tile %d to tile %d%n", player, ladders[i][0], ladders[i][1]);
        System.out.printf("%s is now on tile %d%n", player, player.getPosition());
    }

    /**
     * this method checks if a player is at the head of a snake or bottom of a ladder.
     */
    protected void checkForSnakesOrLadders() {
        //this checks for snakes
        for(int i = 0; i < NUM_SNAKES; i++) {
            if(snakes[i][0] == playerOne.getPosition()) {
                playerAtSnake(playerOne, i);
            } else if(snakes[i][0] == playerTwo.getPosition()) {
                playerAtSnake(playerTwo, i);
            }
        }

        //this checks for ladders
        for(int i = 0; i < NUM_LADDERS; i++) {
            if(ladders[i][0] == playerOne.getPosition()) {
                playerOnLadder(playerOne, i);
            } else if(ladders[i][0] == playerTwo.getPosition()) {
                playerOnLadder(playerTwo, i);
            }
        }
    }
    // ----------------------------------------------------------------- //


    // ----------------------------------------------------------------- //
    /**
     * this method attempts to break a tie when both players have the same
     * dice-values at initial game start.
     */
    private int breakTie() {
        int trials = 1; //count number of trials it took to break the tie.

        do {
            //check if both players have the same dice-value.
            if(players[0].getDiceValue() == players[1].getDiceValue()) {
                System.out.printf("%s rolled a dice-value of %d%n", players[0], players[0].getDiceValue());
                System.out.printf("%s rolled a dice-value of %d%n", players[1], players[1].getDiceValue());

                System.out.printf("A tie was achieved between %s and %s. Attempting to break the tie.%n", players[0],
                        players[1]);
                trials++;

                for(Player p : players) {
                    p.setDiceValue(flipDice());
                }
            }

            //checks if tie has been broken
            if(players[0].getDiceValue() != players[1].getDiceValue()) {
                System.out.printf("%s rolled a dice-value of %d%n", players[0], players[0].getDiceValue());
                System.out.printf("%s rolled a dice-value of %d%n", players[1], players[1].getDiceValue());
                break;
            }
        } while(players[0].getDiceValue() == players[1].getDiceValue());

        return trials;
    }

    /**
     * this method decides the order of play.
     * 
     * @param p1 player 1
     * @param p2 player 2
     */
    private void decideOrderOfPlay(Player p1, Player p2) {
        int trials = breakTie();

        p1 = players[0];
        p2 = players[1];
        System.out.printf("Decision on order of play: %s, then %s. It took %d attempt(s) to break the tie.%n", p1, p2, trials);
    }
    // ----------------------------------------------------------------- //


    // ----------------------------------------------------------------- //
    private void takeTurns(Player p1, Player p2) {
        //moves the player by the dice-value rolled
        p1.setDiceValue(flipDice());
        board.movePlayerOnBoard(p1, p1.getDiceValue());

        System.out.printf("%s rolled a dice-value of %d, and has now moved to tile %d%n", p1, p1.getDiceValue(), p1.getPosition());
        //checks for snakes or ladders on the gameboard
        checkForSnakesOrLadders();

        //check if a player has caught up to the other. if so, the player who landed first is moved back 0.
        if(p1.getPosition() == p2.getPosition()) {
            p2.setPosition(0);
            System.out.printf("%s has caught up to %s at tile %d%n", p1, p2, p1.getPosition());
            System.out.printf("%s has been moved to tile %d%n", p2, p2.getPosition());    
        }
    }

    /**
     * this method starts the game.
     */
    public void play() {
        //roll the dice for each player
        for(Player player : players) {
            player.setDiceValue(flipDice());
        }
        System.out.println("\nNow deciding which player will be starting;");

        //decide the order of play based on which player has a higher dice-value
        if(players[0].getDiceValue() > players[1].getDiceValue()) {
            decideOrderOfPlay(playerOne, playerTwo);
        } else if(players[0].getDiceValue() < players[1].getDiceValue()) {
            decideOrderOfPlay(playerTwo, playerOne);
        }

        //runs until a winner is determined
        while(!isGameOver) {
            //moves the first player by the dice-value rolled
            takeTurns(playerOne, playerTwo);
            //moves the second player by the dice-value rolled
            takeTurns(playerTwo, playerOne);

            //updates the players' positions.
            board.updateBoard(players);

            //if no player is at square 100.
            if(!isGameOver) {
                System.out.println("Game not over; flipping again!\n-----------------------------------------------------------------------------------------------------");
            }
            System.out.println();
        }
    }
    //----------------------------------------------------------------- //
}