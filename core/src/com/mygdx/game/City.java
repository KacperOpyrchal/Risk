package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper Opyrchal on 08.02.2017.
 * Improved by Marcin Holota on 17.02.2017.
 */

public class City {

    private int army;
    private int population;
    private int money;
    private int materials;

    private String membership;


    List<Cell> cells;

    public City() {
        this.army = 2;
        this.materials = 2;
        this.membership = "WILD";
        this.population = 2;
        this.money = 5;

    }

    public City(int army, int materials, String membership, int population, int money, Cell[][] cells, boolean[][] citiesBoolean, float a, int xSize, int ySize, Color color) {
        this.army = army;
        this.materials = materials;
        this.membership = membership;
        this.population = population;
        this.money = money;

        this.cells = new ArrayList<Cell>();

        ShapeOfCity shape = new ShapeOfCity(xSize,ySize,cells,citiesBoolean,this.cells,color,a,10);

    }

}
