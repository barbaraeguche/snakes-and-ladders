# snakes and ladders 🐍🪜
an implementation of the classic board game **snakes and ladders**. it is a two-player board game that simulates the traditional gameplay of moving across a 10x10 grid. players take turns rolling a dice to advance their positions, encountering ladders that elevate them and snakes that send them back. the objective is to be the first to reach square 100.

## features 👾
  - **unique player initialization**: the game initializes with exactly two players, ensuring a competitive environment.
  - **dice rolling mechanism**: players take turns in rolling a dice to determine their movement, with random values between 1 and 6.
  - **game logic**: the game includes rules for moving up ladders and down snakes, as well as handling ties in dice rolls.
  - **user-friendly interface**: clear prompts and messages guide players through the game, enhancing the overall experience.

## game play 🎲
- players take turns rolling a dice to determine their movement on the board.
- the game starts with both players at square 0, outside the board.
- players move forward based on the dice value rolled.
- if a player lands on a square with the bottom of a ladder, they climb to the top of the ladder.
- if a player lands on a square with the head of a snake, they slide down to the tail.
- the first player to reach exactly square 100 wins the game.
- if a player rolls a number that exceeds the moves needed to reach 100, they move backward accordingly.
- players cannot occupy the same square; if they do, the player who lands last sends the other back to square 0.
- the game continues until one player wins, with clear messages displayed after each turn.

## running the project 🏁
to get the project up and running on your local machine, follow these steps:

- **ensure jdk is installed:** must have the [latest jdk](https://www.java.com/en/download/manual.jsp) installed.
- **clone the repository:**
```bash
git clone https://github.com/barbaraeguche/snakes-and-ladders.git
```
- **navigate to the project directory:**
```bash
cd snakes-and-ladders
```
- **run the project:**
```bash
javac GamePlay.java
java GamePlay
```

## gallery 📸
<details>
  <summary>showcase</summary>

  - **initial run**
  

  - **both players on same tile**
  

  - **landed on a ladder**
  

  - **landed on a snake**
  

  - **game won**
  
</details>