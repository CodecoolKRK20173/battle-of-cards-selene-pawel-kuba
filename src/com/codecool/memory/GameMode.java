package com.codecool.memory;
import java.util.List;
import java.util.ArrayList;

public abstract class GameMode {
    public  int baseWidth;
    public int baseHeight;
    public int numOfCards;
    public int numOfRows;
    public int width;

    public int getNumOfCards() {
        return this.numOfCards;
    }

    public ArrayList<Integer> getAllStats() {
        ArrayList<Integer> allStatsList = new ArrayList<>();
        allStatsList.add(0, baseWidth);        
        allStatsList.add(1, baseHeight);
        allStatsList.add(2, numOfCards);
        allStatsList.add(3, numOfRows);
        allStatsList.add(4, width);

        return allStatsList;
    }
}