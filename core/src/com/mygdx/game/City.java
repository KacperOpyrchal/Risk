package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper Opyrchal on 08.02.2017.
 * Improved by Marcin Holota on 17.02.2017.
 * Updated by Kacper Opyrchał on 17.02.2017
 */

public class City {

    private int army;
    private int population;
    private int money;
    private int materials;

    private String membership;
    private ShapeOfCity shape;


    private List<Cell> cells;

    public City(int army, String membership, Board board, Color color) {
        this.army = army;
        this.membership = membership;

        this.cells = new ArrayList<Cell>();

        shape = new ShapeOfCity(board, this.cells, color, 5);

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
