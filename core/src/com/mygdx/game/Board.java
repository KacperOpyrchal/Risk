package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kacper Opyrchal on 04.02.2017.
 * Updated by Marcin Holota on 13.02.2017.
 */

public class Board {

    int xSize;
    int ySize;



    Cell[][] cells;
    List<City> cities;

    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        cells = new Cell[xSize][ySize];
        createBoard();
    }

    private void createBoard(){

        float k = (float)(3*xSize + 0.5);
        float a = Gdx.graphics.getWidth() / k;

        for(int i = 0; i < ySize; ++i){
            for(int j = 0; j < xSize; ++j){
                Color color = Color.BLACK;
                Random rand = new Random();

                switch(rand.nextInt(5)){
                    case 0:
                        color = Color.GREEN;
                        break;
                    case 1:
                        color = Color.RED;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    case 3:
                        color = Color.GOLD;
                        break;
                    case 4:
                        color = Color.ORANGE;
                        break;
                }

                cells[j][i] = new Cell(j, i, a, color);
            }
        }
    }

    public void drawBoard(){
        for(int i = 0; i < ySize; ++i){
            for(int j = 0; j < xSize; ++j){

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
