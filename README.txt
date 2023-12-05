## JavaFX Board Game

## Description
This is a board game written in Java and JavaFX. The game is called Simon’s Obstacle Course.
Simon's Obstacle Course is a board game with a start line and a finish line, as well as obstacles between the two lines.
The goal is simple: the first player to get from start to finish is the winner.Players move around the board, encountering different obstacles, with the goal of reaching the end.

## Features
- Graphical user interface
- Players move around the board
- Random obstacles and events
- Records the number of player moves and game results
- Showing the top ten scores (throws) and player names recorded in this game

## Classes and key functions
### `GameApplication``: Initializes and displays the main application.
- `start(Stage stage)`: Initialize and display the main screen of the application.

### `GameController`.
- `initialize()`: Initialize the game, setting up players and the game board.
- `readPlayerNames()`: Read player names from a file.
- `initializeBoard()`: Initialize the board and obstacles.
- `positionPlayersInView(int x, int y)`: Set the initial position of players on the game board.
- `updatePlayerPosition(ImageView playerView, int newX, int newY)`: Update the position of the player on the game board.
- `rollDice()`: Dice roll logic to randomize player movement.
- `movePlayer(int playerNumber, int steps)`: Moves the player based on the number of points rolled.
- `checkForWin(int[] position, int playerNumber)`: Checks to see if a player has won.
- `checkForObstacles(int[] position, int playerNumber)`: Checks if there are obstacles at the player's position.
- `encounterObstacle(Player player, Obstacle obstacle)`: Handle when player encounters obstacle.
- `updateTextFlow(String message)`: Update the textflow to show the message.
- `writeScoreToFile(int winNumber, int throwCount)`: Write game score to file.
- `writeTop10Scores()`: Write the top 10 scores of the game to a file.
- `rank()`: Read and display the game rank.

### `GameResult`.
- `getPlayer()`: Get the player name.
- ``getThrowCount()``: Get the number of times the player has thrown.

### `Obstacle`.
- `getType()`: Get the type of obstacle.
- `getPosition()`: Get the position of the obstacle.
- `isAtPosition(int[] playerPosition)`: Check if the player is at the obstacle position.

### `Player`.
- `getPosition()`: Get the player's current position.
- `isStoppedBySpike()`: Check if the player is stopped by spike.
- `setStoppedBySpike(boolean stopped)`: Set the state of player stopped by spike.
- `move(int steps)`: Move the player according to the number of points thrown.
- `teleportToRandomLocation()`: Teleports the player to a random location on the board.
- `reborn()`: The player respawns to the starting position.
- `incrementThrowCount()`: Increases the player's throw count.
- `getThrowCount()`: Get the player's throw count.
- `calculateDistance(int x, int y)`: Calculate the distance from the player's start position to the current position.

### `StartButtonController`: Switch to the board.fxml interface
- `handleStartGame()`: Logic to start the game.
- `someMethod()`: Read and save the player name.

### `PlayerTest`.
- Contains unit test methods that test various features of the `Player` class.

### Installation
1. Make sure you have Java and JavaFX installed on your machine.
2. Clone or download this project. 3.
3. Open the project using an IDE suitable for JavaFX, such as IntelliJ IDEA or Eclipse.

## Use
Run `GameApplication.java` in the IDE to start the game.

## Test
Run `PlayerTest.java` to perform unit tests on the `Player` class.

## Contribute
If you want to contribute code to this project, you can do so by submitting pull requests.

## Author
Jingzhi Zhou and Ziyuan Ning.
