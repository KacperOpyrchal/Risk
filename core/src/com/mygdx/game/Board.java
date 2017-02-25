package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
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

    ShapeOfCity shape;



    public Cell[][] cells;
    public boolean[][] citiesBoolean;
    public List<City> cities;
    public boolean[][] inMap;

    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        cells = new Cell[this.xSize][this.ySize];
        citiesBoolean = new boolean[this.xSize][this.ySize];
        inMap = new boolean[this.xSize][this.ySize];
        float k = (float)(3*xSize + 0.5);
        a = Gdx.graphics.getWidth() / k;
        cities = new ArrayList<City>();
        createShapeOfBoard();
        createMap();
        createBoard();
    }


    private void createBoard(){


        Pixmap pixmap = PixMaps.pixMapOne;

        for(int i = 0; i < 15; i++){
            switch (i%3){
                case 0:
                    pixmap = PixMaps.pixMapTwo;
                    break;
                case 1:
                    pixmap = PixMaps.pixMapThree;
                    break;
                case 2:
                    pixmap = PixMaps.pixMapFour;
                    break;
            }

            City newCity = new City(5, "aaaa", this, pixmap);
            cities.add(newCity);

        }
        pixmap = PixMaps.pixMapOne;
        FillEmptySpace fill = new FillEmptySpace(this, pixmap);

        cities.get(0).findBorders(); // dziwne, ale nie crashuje!!!!!!!!!!!!!!!

    }

    private void createMap(){
        Pixmap pixmap = PixMaps.pixMapOne;

        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                if(!inMap[i][j])
                    cells[i][j] = new Cell(i, j, a, pixmap, this);
            }
        }
    }

    private void createShapeOfBoard(){
        shape = new ShapeOfCity(this, 0);
    }


    public void drawBoard(){

        PolygonSpriteBatch polygonSpriteBatch = new PolygonSpriteBatch(6);

        for(int i = 0; i < ySize; ++i){
            for(int j = 0; j < xSize; ++j){
                    polygonSpriteBatch.begin();
                    polygonSpriteBatch.draw(cells[j][i].polygonRegion, cells[j][i].realX, cells[j][i].realY);
                    polygonSpriteBatch.end();
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
