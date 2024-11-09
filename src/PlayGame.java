import java.util.Scanner;

/**
 * this is the main class that starts the game.
 */
public class PlayGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            String playerOneName, playerTwoName;

            //prints the welcome message
            System.out.println("""
                \n~~~~~~~~~~~~~~~~~~~~~~~~~~ Welcome to Snakes & Ladders ~~~~~~~~~~~~~~~~~~~~~~~~~~
                    The following are the rules of the game:
                    - The game will be played by only two players.

                    - Before any of the players start playing, the order of playing turns must be determined. 
                    For that, each player must throw the die to obtain the largest possible number. 
                    In case of a tie between the players, the process is repeated until the order of playing is determined.
                        
                    - At this point, the players start playing the game by alternating dice flipping.
                    
                    - Each dice flip will move a player from tile 0 (which you can think about it as outside the board) with the value of the dice. 
                    For example, if a player is at tile 0 and the dice value was 5, then the player moves to tile 5.
                        
                    - If the reached tile has a bottom of a ladder, then the player moves up the tile that has the top of the ladder. 
                    For instance, if a player is at tile 33, and the flipped dice value was 3, then the player moves to tile 36, which in turn will end moving the player up to tile 44.                 
                        
                    - If the reached tile has a head of a snake, then the player moves down the tile that has the tail of the snake. 
                    For instance, if a player is at tile 77, and the flipped dice value was 2, then the player moves to tile 79 (which has the tip of the snake's head), which in turn end moving the player back to tile 19.
                    
                    - The game is concluded once one of the players EXACTLY reaches tile 100.
                        
                    - If a player is close to 100, and the dice value exceeds the maximum possible moves, the player moves backward with the excessive amount. 
                    For instance, if a player is at tile 96, and the dice value is 5, then the player moves to 99 (that is 4 moves to 100, then 1 move backward to 99).
                """);

            //take user input
            System.out.print("Enter a single name for Player 1: ");
            playerOneName = scanner.next();

            System.out.print("Enter a single name for Player 2: ");
            playerTwoName = scanner.next();

            //initializes the ladderandsnake class to start the game
            SnakeLadder ladderAndSnake = new SnakeLadder(playerOneName, playerTwoName);
            //start the game
            ladderAndSnake.play();
        scanner.close();
    }
}