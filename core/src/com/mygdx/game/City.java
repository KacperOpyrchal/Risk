package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JavaDev on 08.02.2017.
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
        this.money = 2;

        generateCity();
    }

    public City(int army, int materials, String membership, int population, int money) {
        this.army = army;
        this.materials = materials;
        this.membership = membership;
        this.population = population;
        this.money = money;

        generateCity();
    }

    private void generateCity(){
        cells = new ArrayList<Cell>();

        cells.add(new Cell());
    }
}
