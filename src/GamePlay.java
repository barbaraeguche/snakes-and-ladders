import java.util.Scanner;

/**
 * This is the PlayLadderandSnake Class. It is the main class for the LadderandSnake program. It implements printing the welcome message, prompting users for number of players,
 * initializing the LadderandSnake Class, and finally calling the method that runs the game/program.
 */
public class GamePlay {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

            //prints the welcome message
            System.out.println("""
                    --------- Welcome to Snakes & Ladders -----------
                    
                    ------------- The Rules of the game -------------
                    1. The game will be played by only two players.
                    2. Before any of the players start playing, the order of playing turns must be determined. For that, each player must throw the die to obtain the largest possible number. In case of a tie between the players, the process is repeated until the order of playing is determined.
                    3. At this point, the players start playing the game by alternating dice flipping.
                    4. Each dice flip will move a player from square 0 (which you can think about it as outside the board) with the value of the dice. For example, if a player is at square 0 and the dice value was 5, then the player moves to square 5.
                    5. If the reached square has a bottom of a ladder, then the player moves up the square that has the top of the ladder. For instance, if a player is at square 33, and the flipped dice value was 3, then the player moves to square 36, which in turn will end moving the player up to square 44.                 
                    6. If the reached square has a head of a snake, then the player moves down the square that has the tail of the snake. For instance, if a player is at square 77, and the flipped dice value was 2, then the player moves to square 79 (which has the tip of the snake's head), which in turn end moving the player back to square 19.
                    7. The game is concluded once one of the players EXACTLY reaches square 100.
                    8. If a player is close to 100, and the dice value exceeds the maximum possible moves, the player moves backward with the excessive amount. For instance, if a player is at square 96, and the dice value is 5, then the player moves to 99 (that is 4 moves to 100, then 1 move backward to 99).
                    """);

            //prompt the use for number of players
            System.out.print("\nEnter the number of players to begin: ");
            int players = scan.nextInt();

            //initializes the ladderandsnake class to start the game
            SnakeNLadder ladderAndSnake = new SnakeNLadder(players);

            //method in the ladderandsnake class that runs the game
            ladderAndSnake.play();
        scan.close();
    }
}