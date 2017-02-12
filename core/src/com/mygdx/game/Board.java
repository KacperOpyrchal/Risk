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

        float a = Gdx.graphics.getWidth() / xSize;

        a /= 2;

        for(int i = 0; i < ySize; ++i){
            for(int j = 0, jn = 0; j < xSize; ++j, ++jn){

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

                cells[j][i] = new Cell( (jn*3*a)+a, (i*2*a)+a, a, color);

                float x = (jn*3*a)+a;
                float y = (i*2*a)+a;
                j++;

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

                if(j < xSize){
                    cells[j][i] = new Cell( x + (3*a)/2, y + (a*(float)Math.sqrt(3))/2, a, color);
                }

                // Dodawanie cell do tablicy board...
                // funkcja do tworzenia panstw...
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
