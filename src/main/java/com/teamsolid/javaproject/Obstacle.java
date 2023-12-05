package com.teamsolid.javaproject;

public class Obstacle {
    private String type; // Type of obstacle, which can be "fire", "black hole", "ground spike", "portal"
    private int[] position; // Position of obstacles on the board

    public String getType() {
        return type;
    }

    public int[] getPosition() {
        return position;
    }

    public Obstacle(String type, int[] position) {
        this.type = type;
        this.position = position;
    }

    public boolean isAtPosition(int[] playerPosition) {
        return position[0] == playerPosition[0] && position[1] == playerPosition[1];
    }
}