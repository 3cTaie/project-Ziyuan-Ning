package com.teamsolid.javaproject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GameController {
    @FXML
    private GridPane gameBoard;

    private Obstacle[] obstacles; // Array of stored obstacles

    @FXML
    private ImageView imageView; // This is a reference to ImageView in FXML
    private ImageView player1View;
    private ImageView player2View;

    @FXML
    private Button rollButton; // This is a reference to Button in FXML
    @FXML
    private TextFlow textFlow;
    private int currentPlayer;
    private Player player1;
    private Player player2;
    private int winnumber;


    File file1 = new File("src/main/resources/images/dice_1.png");
    String dice1 = file1.toURI().toString();

    File file2 = new File("src/main/resources/images/dice_2.png");
    String dice2 = file2.toURI().toString();

    File file3 = new File("src/main/resources/images/dice_3.png");
    String dice3 = file3.toURI().toString();
    File file4 = new File("src/main/resources/images/dice_4.png");
    String dice4 = file4.toURI().toString();
    File file5 = new File("src/main/resources/images/dice_5.png");
    String dice5 = file5.toURI().toString();
    File file6 = new File("src/main/resources/images/dice_6.png");
    String dice6 = file6.toURI().toString();
    File file7 = new File("src/main/resources/images/dice_7.png");
    String dice7 = file7.toURI().toString();
    File file8 = new File("src/main/resources/images/dice_8.png");
    String dice8 = file8.toURI().toString();
    File file9 = new File("src/main/resources/images/dice_9.png");
    String dice9 = file9.toURI().toString();
    File file_blackhole = new File("src/main/resources/images/blackhole.png");
    String blackhole = file_blackhole.toURI().toString();
    File file_fire = new File("src/main/resources/images/fire.png");
    String fire = file_fire.toURI().toString();
    File file_spike = new File("src/main/resources/images/spike.jpg");
    String spike = file_spike.toURI().toString();
    File file_end = new File("src/main/resources/images/end.png");
    String end = file_end.toURI().toString();
    File P1 = new File("src/main/resources/images/p1.png");
    String p1 = P1.toURI().toString();
    File P2 = new File("src/main/resources/images/p2.png");
    String p2 = P2.toURI().toString();

    // Store the filenames of the nine images
    private final String[] imageFiles = {
            dice1,
            dice2,
            dice3,
            dice4,
            dice5,
            dice6,
            dice7,
            dice8,
            dice9
    };

    // Creating a Random Number Generator
    private final Random random = new Random();

    // Initialization method for the controller, called automatically by the FXML loader when loading an FXML file
    @FXML
    private void initialize() {
        rank();
        initializeBoard();
        player1 = new Player();
        player2 = new Player();
        rollButton.setOnAction(event -> rollDice());

        // Reading names from a file
        List<String> names = readPlayerNames();
        String name1 = names.size() > 0 ? names.get(0) : "Player 1";
        String name2 = names.size() > 1 ? names.get(1) : "Player 2";

        // Random selection of starting players
        currentPlayer = random.nextInt(2) + 1; // Generate 1 or 2
        if (currentPlayer == 1) {
            updateTextFlow(name1 + " starts.");
        } else {
            updateTextFlow(name2 + " starts.");
        }
    }
    private List<String> readPlayerNames() {
        List<String> names = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/players.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming one name per line in the file
                names.add(line.split(": ")[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
    private void initializeBoard() {
        // Initialize the grid of the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rect = new Rectangle(100, 100);
                rect.setStroke(Color.BLACK); // Set the border color of the grid
                rect.setFill((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY); // alternate color
                gameBoard.add(rect, j, i);
            }
        }
        // Initializing obstacles
        obstacles = new Obstacle[7];
        // Specify the location of each obstacle
        int[][] positions = {{3,0},{1, 1}, {6, 1}, {1, 6},{6,7}, {6, 6}, {3, 4}}; // designated location
        String[] types = {"Fire", "BlackHole", "Spike","Fire","BlackHole", "Spike", "End"}; // Specify type
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            String type = types[i];

            Obstacle obstacle = new Obstacle(type, new int[]{x, y});
            obstacles[i] = obstacle;

            ImageView obstacleView = createObstacleView(type, x, y);
            gameBoard.add(obstacleView, x, y);
        }

        // Initializing the Player View
        player1View = createPlayerView(p1); // Replace the image path with that of player 1
        player2View = createPlayerView(p2); // Replace the image path with that of player 2

        // Placing the player at the start of the board
        positionPlayersInView(0, 0);

    }
    private void positionPlayersInView(int x, int y) {
        // Make sure the view is only added once
        if (!gameBoard.getChildren().contains(player1View)) {
            gameBoard.add(player1View, x, y);
        }
        GridPane.setConstraints(player1View, x, y);
        player1View.setTranslateX(0); // Player 1 shifts to the left

        if (!gameBoard.getChildren().contains(player2View)) {
            gameBoard.add(player2View, x, y);
        }
        GridPane.setConstraints(player2View, x, y);
        player2View.setTranslateX(50); // Player 2 shifts to the right
    }

    // Method of updating the player's location
    public void updatePlayerPosition(ImageView playerView, int newX, int newY) {
        GridPane.setConstraints(playerView, newX, newY);
        // If two players are on the same grid, adjust their positions
        if (GridPane.getColumnIndex(player1View) == GridPane.getColumnIndex(player2View) &&
                GridPane.getRowIndex(player1View) == GridPane.getRowIndex(player2View)) {
            player1View.setTranslateX(0); // offset to the left
            player2View.setTranslateX(50); // Right offset
        } else {
            player1View.setTranslateX(0); // reset offset
            player2View.setTranslateX(0); // reset offset
        }
    }

    private ImageView createPlayerView(String imagePath) {
        ImageView view = new ImageView(new Image(imagePath));
        view.setFitWidth(50);
        view.setFitHeight(50);
        return view;
    }

    private ImageView createObstacleView(String type, int x, int y) {
        ImageView view = new ImageView();
        Image image = switch (type) {
            case "Fire" -> new Image(fire);
            case "BlackHole" -> new Image(blackhole);
            case "Spike" -> new Image(spike);
            case "End" -> new Image(end);
            default -> null;
        };
        view.setImage(image);
        view.setFitWidth(100);
        view.setFitHeight(100);
        return view;
    }

    // Show a random picture
    private void rollDice(){
        // Generate a random index to select images
        int randomIndex = random.nextInt(imageFiles.length);

        // Get the name of the selected image file
        String selectedImageFile = imageFiles[randomIndex];

        System.out.println(selectedImageFile+"\n\n\n");

        Image image = new Image(selectedImageFile);
        imageView.setImage(image);

        textFlow.getChildren().clear();
        List<String> names = readPlayerNames();
        String name1 = names.size() > 0 ? names.get(0) : "Player 1";
        String name2 = names.size() > 1 ? names.get(1) : "Player 2";
        if (currentPlayer ==1)
            updateTextFlow(name1 +  " rolls number " + (randomIndex+1));
        else updateTextFlow(name2 +  " rolls number " + (randomIndex+1));
        Player player = (currentPlayer == 1) ? player1 : player2;
        player.incrementThrowCount();
        if(player.isStoppedBySpike()&&(randomIndex+1)<3){
            updateTextFlow("\nThe number is less than 3, you have to wait one round!!!");
        } else if (player.isStoppedBySpike()&&(randomIndex+1)>=3) {
            player.setStoppedBySpike(false);
        }
        else{
            movePlayer(currentPlayer,randomIndex+1);}

        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    private void movePlayer(int playerNumber, int steps) {
        ImageView playerView = (playerNumber == 1) ? player1View : player2View;
        Player player = (playerNumber == 1) ? player1 : player2;

        player.move(steps); // 更新玩家位置
        int[] newPosition = player.getPosition();
        checkForObstacles(newPosition,playerNumber);
        updatePlayerPosition(playerView,player.getPosition()[0],player.getPosition()[1]);
        winnumber = checkForWin(newPosition,playerNumber);
    }

    private int checkForWin(int[] position, int playerNumber) {
        if (position[0] == 3 && position[1] == 4) {
            // Player wins
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);

            // Get the name of the winning player
            List<String> names = readPlayerNames();
            String name1 = names.size() > 0 ? names.get(0) : "Player 1";
            String name2 = names.size() > 1 ? names.get(1) : "Player 2";
            String winningPlayerName = (playerNumber == 1) ? name1 : name2;

            alert.setContentText(winningPlayerName + " wins!!!");
            alert.showAndWait();

            Player winningPlayer = (playerNumber == 1) ? player1 : player2;
            writeScoreToFile(playerNumber, winningPlayer.getThrowCount());
            writeTop10Scores();
            System.exit(0); // Shut down the program
            return playerNumber; // Returns the winner's number
        }
        return -1; // No player wins.
    }

    private void checkForObstacles(int[] position, int playerNumber) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isAtPosition(position)) {
                Player player = (playerNumber == 1) ? player1 : player2;
                encounterObstacle(player,obstacle);
                break; // Assuming only one obstacle per grid
            }
        }
    }

    private void encounterObstacle(Player player, Obstacle obstacle){
        switch (obstacle.getType()){
            case "Fire":
                player.reborn(); // Encountered fire, burned to death starting point resurrection
                updateTextFlow("\nYou were burned to death. Lets start from the beginning!!!");
                break;
            case "BlackHole":
                player.teleportToRandomLocation(); // Encountered a black hole and teleported to a random location
                updateTextFlow("\nOps! Where are you?");
                break;
            case "Spike":
                player.setStoppedBySpike(true);
                break;
        }
    }

    private void updateTextFlow(String message) {
        Text text = new Text(message ); // Add line breaks so that the next message appears on a new line
        text.setFont(new Font("Arial",20));
        textFlow.getChildren().add(text);
    }

    private void writeScoreToFile(int winNumber, int throwCount) {
        try {
            List<String> names = readPlayerNames();
            String name1 = names.size() > 0 ? names.get(0) : "Player 1";
            String name2 = names.size() > 1 ? names.get(1) : "Player 2";
            String winnerName = (winNumber == 1) ? name1 : name2;

            FileWriter write = new FileWriter("src/main/resources/game_score.txt", true);
            BufferedWriter bw = new BufferedWriter(write);

            bw.write("Winning player: " + winnerName + "\n");
            bw.write("Number of throws: " + throwCount + "\n");

            // Closing the file is handled in the final block, ensuring that the file is closed properly even if an exception occurs
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeTop10Scores() {
        List<GameResult> gameResults = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/game_score.txt"))) {
            String line;
            String currentPlayer = null;
            int currentThrowCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Winning player:")) {
                    if (currentPlayer != null) {
                        gameResults.add(new GameResult(currentPlayer, currentThrowCount));
                    }
                    currentPlayer = line.substring("Winning player: ".length());
                } else if (line.startsWith("Number of throws:")) {
                    try {
                        currentThrowCount = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
                    } catch (NumberFormatException e) {
                        // Handle invalid integer, skip this entry
                    }
                }
            }
            // Add the last result if it exists
            if (currentPlayer != null) {
                gameResults.add(new GameResult(currentPlayer, currentThrowCount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort the game results by throw count in ascending order
        gameResults.sort(Comparator.comparingInt(GameResult::getThrowCount));

        // Select the top 10 results
        List<GameResult> top10Results = gameResults.subList(0, Math.min(10, gameResults.size()));

        // Write the top 10 results to rank.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/rank.txt"))) {
            bw.write("Top 10 Results:\n");
            for (int i = 0; i < top10Results.size(); i++) {
                GameResult result = top10Results.get(i);
                bw.write("Rank " + (i + 1) + ": " + result.getPlayer() + ", Number of throws: " + result.getThrowCount() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextFlow rankTextFlow;

    public void rank() {
        try {
            // Read the contents of the rank.txt file
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/rank.txt"), StandardCharsets.UTF_8);

            // Create a Text object to display the contents of the file
            Text text = new Text(String.join("\n", lines));

            // Adding Text Objects to TextFlow
            rankTextFlow.getChildren().add(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}