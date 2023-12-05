package com.teamsolid.javaproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StartButtonController {
    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;
    public void handleStartGame() {
        try {
            // Close the current window
            Stage stage = (Stage) player1Name.getScene().getWindow();
            stage.close();
            // Load board.fxml
            Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
            // Show New Window
            Stage boardStage = new Stage();
            boardStage.setScene(new Scene(root));
            boardStage.setTitle("Game Board");
            boardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void someMethod() {
        String name1 = player1Name.getText();
        String name2 = player2Name.getText();
        try {
            // Creating a BufferedWriter instance to write to a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/players.txt"));

            // Write the player's name
            writer.write("Player 1: " + name1 + "\n");
            writer.write("Player 2: " + name2 + "\n");

            // Close the writer to ensure that data is written to the file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
