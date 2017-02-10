package com.mygdx.game;

import java.util.List;

/**
 * Created by Kacper Opyrchal on 04.02.2017
 * Updated by Marcin Holota on 10.02.2017
 */

public class Cell {

    int x;
    int y;

    List<Cell> neighbours;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    private boolean validate(Cell neighbour, int width, int height){
        boolean correct = true;
        if(neighbour.x < 0 || neighbour.x >= width)
            correct = false;
        if(neighbour.y < 0 || neighbour.y >= height)
            correct = false;
        return correct;
    }

    private Cell topNeighbour(){
        Cell top = new Cell(x, y - 2);
        return top;
    }

    private Cell topLeftNeighbour(){
        Cell topLeft = new Cell(x - y%2, y - 1);
        return topLeft;
    }

    private Cell topRightNeighbour(){
        Cell topRight = new Cell(x + 1 - y%2, y - 1);
        return topRight;
    }

    private Cell bottomNeighbour(){
        Cell bottom = new Cell(x, y + 2);
        return bottom;
    }

    private Cell bottomLeftNeighbour(){
        Cell bottomLeft = new Cell(x - y%2, y + 1);
        return bottomLeft;
    }

    private Cell bottomRightNeighbour(){
        Cell bottomRight = new Cell(x + 1 - y%2, y + 1);
        return bottomRight;
    }

    public void findNeighbours(int width, int height){
        Cell newNeighbour = topNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = topLeftNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = topRightNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = bottomNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = bottomLeftNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = bottomRightNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);
    }
}
