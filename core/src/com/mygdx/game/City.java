package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper Opyrchal on 08.02.2017.
 * Improved by Marcin Holota on 17.02.2017.
 * Updated by Kacper Opyrchał on 17.02.2017
 * Function findBorders has been added by Marcin Holota on 25.02.2017.
 */

public class City {

    private int army;
    private int population;
    private int money;
    private int materials;

    private String membership;
    private ShapeOfCity shape;


    private List<Cell> cells;

    private Board board;

    public City(int army, String membership, Board board, Pixmap pixmap) {
        this.army = army;
        this.membership = membership;

        this.cells = new ArrayList<Cell>();

        this.board = board;

        shape = new ShapeOfCity(board, this.cells, pixmap, 4);

    }

    @Deprecated
    public City(int army, int materials, String membership, int population, int money, Cell[][] cells, boolean[][] citiesBoolean, float a, int xSize, int ySize, Color color) {
        this.army = army;
        this.materials = materials;
        this.membership = membership;
        this.population = population;
        this.money = money;

        this.cells = new ArrayList<Cell>();

        //shape = new ShapeOfCity(xSize,ySize,cells,citiesBoolean,this.cells,color,a,10);

    }

    public void drawBorder(ShapeRenderer shapeRenderer) {

        float x1, y1;
        float x2, y2;

        for (Cell cell : cells) {

            //Cell neighbour = cell.topNeighbour();

            if (cell.borders[0]) {

                x1 = cell.realX - cell.size / 2;
                y1 = cell.realY + (cell.size * (float) Math.sqrt(3)) / 2;

                x2 = cell.realX + cell.size / 2;
                y2 = cell.realY + (cell.size * (float) Math.sqrt(3)) / 2;

                shapeRenderer.rectLine(x1, y1, x2, y2, 4);
            }

            if (cell.borders[1]) {
                x1 = cell.realX + cell.size / 2;
                y1 = cell.realY + (cell.size * (float) Math.sqrt(3)) / 2;

                x2 = cell.realX + cell.size;
                y2 = cell.realY;

                shapeRenderer.rectLine(x1, y1, x2, y2, 4);
            }


            if (cell.borders[2]) {
                x1 = cell.realX + cell.size / 2;
                y1 = cell.realY - (cell.size * (float) Math.sqrt(3)) / 2;

                x2 = cell.realX + cell.size;
                y2 = cell.realY;//

                shapeRenderer.rectLine(x1, y1, x2, y2, 4);
            }

            if (cell.borders[3]) {
                x1 = cell.realX - cell.size / 2;
                y1 = cell.realY - (cell.size * (float) Math.sqrt(3)) / 2;

                x2 = cell.realX + cell.size / 2;
                y2 = cell.realY - (cell.size * (float) Math.sqrt(3)) / 2;

                shapeRenderer.rectLine(x1, y1, x2, y2, 4);
            }

            if (cell.borders[4]) {
                x1 = cell.realX - cell.size / 2;
                y1 = cell.realY - (cell.size * (float) Math.sqrt(3)) / 2;

                x2 = cell.realX - cell.size;
                y2 = cell.realY;

                shapeRenderer.rectLine(x1, y1, x2, y2, 4);
            }

            if (cell.borders[5]) {
                x1 = cell.realX - cell.size / 2;
                y1 = cell.realY + (cell.size * (float) Math.sqrt(3)) / 2;

                x2 = cell.realX - cell.size;
                y2 = cell.realY;

                shapeRenderer.rectLine(x1, y1, x2, y2, 4);
            }


        }


    }

    public void findBorders() {


        /*
        for (Cell cell : cells) {
            Gdx.app.log("Cells", "(" + cell.x + ", " + cell.y + ")");
        }*/

        for (Cell cell : cells) {

            Cell topCell = cell.topNeighbour();
            Cell topLeftCell = cell.topLeftNeighbour();
            Cell topRightCell = cell.topRightNeighbour();
            Cell bottomCell = cell.bottomNeighbour();
            Cell bottomLeftCell = cell.bottomLeftNeighbour();
            Cell bottomRightCell = cell.bottomRightNeighbour();

            Gdx.app.log("Cell", "(" + cell.x + ", " + cell.y + ")");

            if (checkCell(topCell)) {
                cell.borders[0] = true;
            }

            if (checkCell(topRightCell)) {
                cell.borders[1] = true;
            }

            if (checkCell(bottomRightCell)) {
                cell.borders[2] = true;
            }

            if (checkCell(bottomCell)) {
                cell.borders[3] = true;
            }

            if (checkCell(bottomLeftCell)) {
                cell.borders[4] = true;
            }

            if (checkCell(topLeftCell)) {
                cell.borders[5] = true;
            }

            Gdx.app.log("===", "======================================");


        }

    }

    public boolean checkCell(Cell check) {

        Gdx.app.log("Neigh", "(" + check.x + ", " + check.y + ")");

        for (Cell cell : cells) {

            if (cell.x == check.x && cell.y == check.y) {
                return false;
            }
        }

        return true;
    }

    public void findBorders2() { // powinna działać ta funkcja, ale będzie trzeba to jeszcze sprawdzić

        for (int i = 0; i < cells.size(); i++) {

            Cell cell1 = cells.get(i);
            cell1.findNeighbours();

            for (int j1 = 0, j2 = 0; j1 < 6 || j2 < cell1.neighbours.size(); j1++) {

                boolean border = true;

                if (cell1.neighboursBoolean[j1]) {

                    Cell neighbour = cell1.neighbours.get(j2);

                    for (int k = 0; k < cells.size(); k++) {
                        Cell cell2 = cells.get(k);

                        if (cell2 == neighbour) {
                            border = false;
                            break;
                        }

                    }

                    cell1.borders[j1] = border;

                    j2++;
                }

            }

        }

    }

    public ShapeOfCity getShape() {
        return shape;
    }

    public void setShape(ShapeOfCity shape) {
        this.shape = shape;
    }

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public int getMaterials() {
        return materials;
    }

    public void setMaterials(int materials) {
        this.materials = materials;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
