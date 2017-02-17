package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Kacper Opyrchal on 04.02.2017.
 * Updated by Marcin Holota on 13.02.2017.
 * Cleared by Marcin Holota on 17.02.2017.
 */

public class Board {

    int xSize;
    int ySize;
    float a;



    public Cell[][] cells;
    public boolean[][] citiesBoolean;
    public List<City> cities;

    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        cells = new Cell[this.xSize][this.ySize];
        citiesBoolean = new boolean[this.xSize][this.ySize];
        float k = (float)(3*xSize + 0.5);
        a = Gdx.graphics.getWidth() / k;
        cities = new ArrayList<City>();
        createBoard();
    }


    private void createBoard(){
        Color color = Color.BLACK;
        for(int i = 0; i < 9; i++){
            switch (i%3){
                case 0:
                    color = Color.BLUE;
                    break;
                case 1:
                    color = Color.FOREST;
                    break;
                case 2:
                    color = Color.CHARTREUSE; // chciałem sprawdzić jaki to kolor
                    break;
            }

            //City newCity = new City(5,5,"aaaa",6,6,cells,citiesBoolean,a,xSize,ySize,color);
            City newCity = new City(5, "aaaa", this, color);
            cities.add(newCity);

        }

    }


    public void drawBoard(){
        for(int i = 0; i < ySize; ++i){
            for(int j = 0; j < xSize; ++j){
                if(citiesBoolean[j][i])
                    cells[j][i].drawHexagon();
            }
        }
    }

    public int getX(){
        return xSize;
    }

    public int getY(){
        return ySize;
    }
}
