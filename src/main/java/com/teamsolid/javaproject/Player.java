package com.teamsolid.javaproject;
import java.util.Random;

public class Player {
    private int[] position;
    private int distance;
    private boolean stoppedBySpike;

    public Player() {
        this.position = new int[]{0, 0};
        this.distance = 1;
    }
    public int[] getPosition() {
        return position;
    }
    public boolean isStoppedBySpike() {
        return stoppedBySpike;
    }
    public void setStoppedBySpike(boolean stopped) {
        this.stoppedBySpike = stopped;
    }
    public void move(int steps) {
        distance += steps;

        if(distance>0 && distance<=8){
            this.position[0] = distance-1;
            this.position[1] = 0;
        }
        if(distance>8&&distance<=15){
            this.position[0] = 7;
            this.position[1] = distance-8;
        }
        if(distance>15&&distance<=22){
            this.position[0] = 7-(distance-15);
            this.position[1] = 7;
        }
        if(distance>22&&distance<=28){
            this.position[0] = 0;
            this.position[1] = 7-(distance-22);
        }
        if(distance>28&&distance<=34){
            this.position[0] = distance-28;
            this.position[1] = 1;
        }
        if(distance>34&&distance<=39){
            this.position[0] = 6;
            this.position[1] = distance-34+1;
        }
        if(distance>39&&distance<=44){
            this.position[0] = 6-(distance-39);
            this.position[1] = 6;
        }
        if(distance>44&&distance<=48){
            this.position[0] = 1;
            this.position[1] = 6-(distance-44);
        }

        if(distance>48&&distance<=52){
            this.position[0] = distance-48+1;
            this.position[1] = 2;
        }
        if(distance>52&&distance<=55){
            this.position[0] = 5;
            this.position[1] = distance-52+2;
        }
        if(distance>55&&distance<=58){
            this.position[0] = 5-(distance-55);
            this.position[1] = 5;
        }
        if(distance>58&&distance<=60){
            this.position[0] = 2;
            this.position[1] = 5-(distance-58);
        }

        if(distance>60&&distance<=62){
            this.position[0] = distance-60+2;
            this.position[1] = 3;
        }
        if(distance==63){
            this.position[0] = 4;
            this.position[1] = 4;
        }
        if(distance>=64){
            this.position[0] = 3;
            this.position[1] = 4;
        }
    }

    public void teleportToRandomLocation(){
        Random random = new Random();
        int randomX = random.nextInt(8); // Generate random X-coordinates (0 to 7)
        int randomY = random.nextInt(8); // Generate random Y-coordinate (0 to 7)
        this.position[0] = randomX;
        this.position[1] = randomY;
        this.distance = calculateDistance(this.position[0],this.position[1]);
        System.out.println(this.distance);
    }

    public void reborn(){
        this.distance = 1;
        this.position[0] = 0;
        this.position[1] = 0;
    }
    private int throwCount = 0;

    public void incrementThrowCount() {
        this.throwCount++;
    }

    public int getThrowCount() {
        return this.throwCount;
    }

    public int calculateDistance(int x, int y) {

        int[][] expectedDistances = new int[][]{
                {1, 28, 27, 26, 25, 24, 23, 22},
                {2, 29, 48, 47, 46, 45, 44, 21},
                {3, 30, 49, 60, 59, 58, 43, 20},
                {4, 32, 50, 61, 64, 57, 42, 19},
                {5, 33, 51, 62, 63, 56, 41, 18},
                {6, 34, 52, 53, 54, 55, 40, 17},
                {7, 34, 35, 36, 37, 38, 39, 16},
                {8, 9, 10, 11, 12, 13, 14, 15}
        };

        return expectedDistances[x][y];
    }
}