# snakes and ladders 🐍🪜

## features 👾
- **year extraction:** creates a `{year}.txt` file containing records based on a specified year.
- **record deletion:** removes consecutive repeated records from the list to maintain data integrity.
- **author records:** generates a new list that includes only the records from a specified author.
- **insertion before isbn:** inserts a book object before a record with a given isbn number.
- **insertion between isbns:** inserts a book object between two specified isbn numbers, provided both are found in the list.
- **isbn swaps:** swaps the positions of two isbn numbers in the list if they exist.
- **commit changes:** saves the updated list to a file named `commit.txt` upon command.
- **stop talking:** terminates the conversation and exits the program.

## running the project 🏁
to get the project up and running on your local machine, follow these steps:

- **ensure jdk is installed:** must have [latest jdk](https://www.java.com/en/download/manual.jsp) installed.
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
javac <driver>.java
java <driver>
```

## gallery 📸
<details>
  <summary>showcase</summary>

  - **initial run**
  

  - **option 2**
  

  - **option 3**
  

  - **option 5**
  
</details>







## old readme
<details>
  <summary>readme</summary>

  ### Short Description

  This project is an implementation of the classic board game "Ladder and Snake," designed for two players. It utilizes object-oriented programming principles to create a 10x10 game board, where players navigate through ladders and snakes based on dice rolls. The game emphasizes user interaction, input validation, and clear output messaging to enhance the gaming experience.
  
  ### README Definition
  
  ## Ladder and Snake Game
  
  ### Overview
  The **Ladder and Snake** game is a two-player board game that simulates the traditional gameplay of moving across a 10x10 grid. Players take turns rolling a dice to advance their positions, encountering ladders that elevate them and snakes that send them back. The objective is to be the first to reach square 100.
  
  ### Features
  - **Unique Player Initialization**: The game initializes with exactly two players, ensuring a competitive environment.
  - **Dice Rolling Mechanism**: Players roll a dice to determine their movement, with random values between 1 and 6.
  - **Game Logic**: The game includes rules for moving up ladders and down snakes, as well as handling ties in dice rolls.
  - **User-Friendly Interface**: Clear prompts and messages guide players through the game, enhancing the overall experience.
</details>
