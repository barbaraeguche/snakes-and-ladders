import java.util.Random;

/**
 * This is the LadderandSnake Class. It implements methods for initializing the number of players in the game, generating random flipped die numbers for each player,
 * setting up the snakes and ladders on the gameboard, creating, showing and updating the gameboard, moving the players on the gameboard,
 * checking for snakes and ladders on the gameboard, and finally running the game.
 */
public class SnakeNLadder {
    private Player[] players = new Player[2];
    private Player firstPlayer, secondPlayer;
    private boolean gameOver = false;

    //constant variables: gameboard size, number of snakes and ladders
    private final int ROWS = 10, COLS = 10, NUM_SNAKES = 8, NUM_LADDERS = 9;

    //gameboard variables
    private int[][] snakes, ladders;
    private final Tiles[][] gameboard = new Tiles[ROWS][COLS];

    /**
     * This is the default constructor
     */
    public SnakeNLadder() {}

    /**
     * This method initializes the number of players for the game, creates and shows the gameboard
     * @param numOfPlayers sets the number of players to be passed
     */
    public SnakeNLadder(int numOfPlayers) {

        //this is checking if the number of players passed is > 2. if so, it prints a warning message and set the number of players to 2.
        if(numOfPlayers > 2) {
            System.out.println("Initialization was attempted for " + numOfPlayers + " member of players; however, this is only expected for an extended "
                    + "version of the game. Value will be set to 2.\n");

            players = new Player[2];
        }

        //this is checking if the number of players passed is < 2. if so, it prints an error message and terminate the program immediately.
        else if(numOfPlayers < 2) {
            System.out.print("Error: Cannot execute the game with less than 2 players! Will exit");
            System.exit(0);
        }

        //creating a new player object for each player in the players array.
        for(int i = 0; i < players.length; i++) {
            players[i] = new Player("P" + i);
        }

        //this is creating and showing the gameboard.
        createBoard();
        showBoard();
    }

    /**
     * This method returns a random number between 1 and 6 inclusively
     * @return a random number between 1 and 6 inclusively
     */
    public static int flipDice() {
        return new Random().nextInt(6) + 1;
    }

    /**
     * This method decides the order of play, and then runs the game
     */
    public void play() {

        //this is setting the dice value for each player in the players array.
        for(Player p : players) {
            p.setDiceValue(SnakeNLadder.flipDice());
        }
        System.out.println("\nNow deciding which player will be starting;");
        int i = 1; //to count the number of attempts it took to reach the decision on the order of play.

        do {
            /*
            this is checking of the dice value for both players are equal. if so, it prints out the dice values of each player, then attempts to break
            the tie by flipping the dice again.
             */
            if(players[0].getDiceValue() == players[1].getDiceValue()) {
                System.out.println(players[0] + " got a dice value of " + players[0].getDiceValue());
                System.out.println(players[1] + " got a dice value of " + players[1].getDiceValue());
                System.out.println("A tie was achieved between " + players[0] + " and " + players[1] + ". Attempting to break the tie\n");
                i++;
                for(Player p : players) {
                    p.setDiceValue(SnakeNLadder.flipDice());
                }
            }

            //this is checking of the dice value for both players are not equal. if so, it prints out the dice values of each player, then breaks out of the loop.
            if(players[0].getDiceValue() != players[1].getDiceValue()) {
                System.out.println(players[0] + " got a dice value of " + players[0].getDiceValue());
                System.out.println(players[1] + " got a dice value of " + players[1].getDiceValue());
                break;
            }
        } while(players[0].getDiceValue() == players[1].getDiceValue());

        //this checks to see which player has flipped the higher dice value. whoever has flipped the higher dice value is set to play first, and the other player is set to play second.
        if(players[0].getDiceValue() > players[1].getDiceValue()) {
            firstPlayer = players[0];
            secondPlayer = players[1];
            System.out.println("Reached final decision on order of playing: " + players[0] + " then " + players[1] + ". It took " + i + " attempts before a decision could be made.\n");
        } else if(players[0].getDiceValue() < players[1].getDiceValue()) {
            firstPlayer = players[1];
            secondPlayer = players[0];
            System.out.println("Reached final decision on order of playing: " + players[1] + " then " + players[0] + ". It took " + i + " attempts before a decision could be made.\n");
        }

        /*
        This code is a while-loop that continues until the game is over. when the game is not over, each player takes a turn by flipping a die and moving the value
        of the die flipped, then it checks if the player has landed of a snake's head or the bottom of a ladder or if the player has caught up to another player on the
        gameboard.
         */
        while(!gameOver) {

            //moves the first player by the dice value rolled.
            firstPlayer.setDiceValue(SnakeNLadder.flipDice());
            movePlayerOnBoard(firstPlayer, firstPlayer.getDiceValue());
            System.out.println(firstPlayer + " got a dice value of " + firstPlayer.getDiceValue() + "; now in square " + firstPlayer.getPosition());
            checkForSnakesAndLadders(); //checks for snakes or ladders on the gameboard.

            //this checks if the first player has caught up to the second player. if so, the second player's position is set to 0.
            if(firstPlayer.getPosition() == secondPlayer.getPosition()) {
                secondPlayer.setPosition(0);
                System.out.println(firstPlayer + " has caught up to " + secondPlayer + " at square " + firstPlayer.getPosition());
                System.out.println(secondPlayer + " has been moved to square " + secondPlayer.getPosition());
            }

            //moves the second player by the dice value rolled.
            secondPlayer.setDiceValue(SnakeNLadder.flipDice());
            movePlayerOnBoard(secondPlayer, secondPlayer.getDiceValue());
            System.out.println(secondPlayer + " got a dice value of " + secondPlayer.getDiceValue() + "; now in square " + secondPlayer.getPosition());
            checkForSnakesAndLadders(); //checks for snakes or ladders on the gameboard.

            //this checks if the second player has caught up to the first player. if so, the first player's position is set to 0.
            if(secondPlayer.getPosition() == firstPlayer.getPosition()) {
                firstPlayer.setPosition(0);
                System.out.println(secondPlayer + " has caught up to " + firstPlayer + " at square " + secondPlayer.getPosition());
                System.out.println(firstPlayer + " has been moved to square " + firstPlayer.getPosition());
            }

            //updates the players' positions.
            updateBoard(players);

            //if no player is at square 100.
            if(!gameOver) {
                System.out.println("Game not over; flipping again!");
                System.out.println("-----------------------------------------------------------------------------------------------------");
            }
            System.out.println();
        }
    }

    /**
     * This method creates new tile objects and sets the tiles to the gameboard
     */
    private void createBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                //this is creating a new Tiles object and passing the tileNumber value to appropriate Tiles constructor.
                int tileNumber = (i * ROWS) + j + 1;
                gameboard[i][j] = new Tiles(tileNumber);
            }
        }
        setSnakes();
        setLadders();
    }

    /**
     * This method prints out the gameboard that visibly shows the tile-numbers/squares of the gameboard and the exact squares the players are on
     */
    private void showBoard() {
        for (int j = 9; j >= 0; j--) {
            if (j % 2 == 0) { //if j is even, print out the tile-number of the gameboard.
                for (int x = 0; x < 10; x++) {
                    System.out.print(gameboard[j][x] + "\t");
                }
            } else { //if j is odd, print out the tile-number of the gameboard.
                for (int x = 9; x >= 0; x--) {
                    System.out.print(gameboard[j][x] + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * This method updates the gameboard to reflect the current position of the players
     * @param players an array of players that are playing the game
     */
    private void updateBoard(Player[] players) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                //this is creating a new Tiles object and passing the tileNumber value to appropriate Tiles constructor.
                int tileNumber = (i * ROWS) + j + 1;
                gameboard[i][j] = new Tiles(tileNumber, players);
            }
        }
        showBoard();
    }

    /**
     * This method is responsible for moving the player on the gameboard
     * @param p is the player object that is being moved
     * @param diceRolled is the dice value that each player rolled
     */
    public void movePlayerOnBoard(Player p, int diceRolled) {
        //this is adding the player's current position to the dice value that was rolled.
        int newPosition = p.getPosition() + diceRolled;

        for (Tiles[] t : gameboard) {
            for(Tiles tile : t) {
                //this checks if the player's current position is within the bounds of the gameboard(1 - 100).
                if (newPosition != tile.getTileNumber()) {
                    if (newPosition > 100) {
                        /*
                        If the player's current position is greater than 100, it sets the player's position to 100 - (new position - 100)
                        that is, if the new position = 103, it sets the player's position to 100 - (103 - 100) => 97.
                         */
                        p.setPosition(100 - (newPosition - 100));
                    }
                }

                //this checks if the player's current position is within the bounds of the gameboard(1 - 100).
                if (newPosition == tile.getTileNumber()) {
                    //if the player's current position is within the bounds of the gameboard(1 - 100), it sets the player's position to the corresponding tile-number/square.
                    p.setPosition(tile.getTileNumber());
                    //this checks if the player has won the game.
                    if (p.getPosition() == 80 || p.hasPlayerWon()) {
                        System.out.println(p + " got a dice value of " + p.getDiceValue() + "; now in square " + p.getPosition());
                        checkForSnakesAndLadders(); //at square 80, the player takes the ladder that goes up to square 100.
                        System.out.println(p + " hits " + p.getPosition());
                        System.out.println(p + " Wins the game!!!");
                        updateBoard(players);
                        gameOver = true;

                        //prints the closing message when the game is over.
                        System.out.println("\nThank you for playing Snakes & Ladders, Goodbye for now...");
                        System.exit(0);
                    }
                }
            }
        }
    }

    /**
     * This method checks each tile-number/square for a snake's head or the bottom of a ladder
     */
    private void checkForSnakesAndLadders() {
        //this is checking for the snakes.
        for(int i = 0; i < NUM_SNAKES; i++) {
            //if the player's current position is at the same tile with a snake's head, the player goes down to the tile with the snake's tail
            if(snakes[i][0] == firstPlayer.getPosition()) {
                firstPlayer.setPosition(snakes[i][1]);
                System.out.println("Uh oh. Snakes takes " + firstPlayer + " from square " + snakes[i][0] + " to square " + snakes[i][1]);
                System.out.println(firstPlayer + " is now in square " + firstPlayer.getPosition());
            } else if(snakes[i][0] == secondPlayer.getPosition()) {
                secondPlayer.setPosition(snakes[i][1]);
                System.out.println("Uh oh. Snakes takes " + secondPlayer + " from square " + snakes[i][0] + " to square " + snakes[i][1]);
                System.out.println(secondPlayer + " is now in square " + secondPlayer.getPosition());
            }
        }

        //this is checking for the ladders.
        for(int i = 0; i < NUM_LADDERS; i++) {
            //if the player's current position is at the same tile with the bottom of a ladder, the player goes up the ladder.
            if(ladders[i][0] == firstPlayer.getPosition()) {
                firstPlayer.setPosition(ladders[i][1]);
                System.out.println("YAYY! Ladder takes " + firstPlayer + " from square " + ladders[i][0] + " to square " + ladders[i][1]);
                System.out.println(firstPlayer + " is now in square " + firstPlayer.getPosition());
            } else if(ladders[i][0] == secondPlayer.getPosition()) {
                secondPlayer.setPosition(ladders[i][1]);
                System.out.println("YAYY! Ladder takes " + secondPlayer + " from square " + ladders[i][0] + " to square " + ladders[i][1]);
                System.out.println(secondPlayer + " is now in square " + secondPlayer.getPosition());
            }
        }
    }

    /**
     * This method sets the snakes on the gameboard
     */
    private void setSnakes() {
        //this is initializing the snakes.
        snakes = new int[NUM_SNAKES][2];
        snakes[0][0] = 16; snakes[0][1] = 6; snakes[1][0] = 48; snakes[1][1] = 30; snakes[2][0] = 64; snakes[2][1] = 60; snakes[3][0] = 79; snakes[3][1] = 19;
        snakes[4][0] = 93; snakes[4][1] = 68; snakes[5][0] = 95; snakes[5][1] = 24; snakes[6][0] = 97; snakes[6][1] = 76; snakes[7][0] = 98; snakes[7][1] = 78;
    }

    /**
     * This method sets the ladders on the gameboard
     */
    private void setLadders() {
        //this is initializing the ladders.
        ladders = new int[NUM_LADDERS][2];
        ladders[0][0] = 1; ladders[0][1] = 38; ladders[1][0] = 4; ladders[1][1] = 14; ladders[2][0] = 9; ladders[2][1] = 31; ladders[3][0] = 21; ladders[3][1] = 42;
        ladders[4][0] = 28; ladders[4][1] = 84; ladders[5][0] = 36; ladders[5][1] = 44; ladders[6][0] = 51; ladders[6][1] = 67; ladders[7][0] = 71; ladders[7][1] = 91;
        ladders[8][0] = 80; ladders[8][1] = 100;
    }
}