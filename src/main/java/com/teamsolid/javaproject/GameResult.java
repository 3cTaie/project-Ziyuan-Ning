package com.teamsolid.javaproject;

public class GameResult {
    private String Player;
    private int throwCount;

    public GameResult(String Player, int throwCount) {
        this.Player = Player;
        this.throwCount = throwCount;
    }

    public String getPlayer() {
        return Player;
    }
    public int getThrowCount() {
        return throwCount;
    }

}
