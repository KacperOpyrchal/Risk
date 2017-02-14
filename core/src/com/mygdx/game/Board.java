package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;
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
        cells = new Cell[this.xSize][this.ySize];
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



    // Punkt D = (xD, yD) to punkt do sprawdzenia czy się zawiera w figurze, Center to punkt ktory został wylosowany i figura jest tworzona na jego podstawie
    private boolean linearInequation(float xA, float yA, float xB,float yB, int xCenter, int yCenter, int xD, int yD){  // A = (xA,yB), B = ...

        float a = (yA - yB)/(xA - xB); // f(x) = ax+b
        float b = yA - a * xA;

        float xCenterResult = a * xCenter + b;
        float xDResult = a * xD + b;

        if(xCenterResult <= yCenter){
            if(xDResult <= yD)
                return true;
        } else {
            if(xDResult >= yD)
                return true;
        }

        return false;
    }


    private void createRandomFigure(int xCenter, int yCenter, int radius, int n, Vector2[] vertices){ // figura jest tworzona na podstawie koła ///  n - ilość wierzchołków

        int angle = 360 / n; // koło jest dzielone na fragmenty

        Random random = new Random();

        for(int i = 0; i < n; i++){

            int alpha = random.nextInt(angle); // losowany jest kąt względem ktorego wyliczany jest punkt
            int distance = random.nextInt(radius + 1); // losowana odleglość od punktu Center

            double radianAngle = i * angle + alpha;

            double x = Math.sin(radianAngle) * distance;
            double y = Math.cos(radianAngle) * distance;

            float newX = (float)(xCenter + x);
            float newY = (float)(yCenter + y);

            vertices[i] = new Vector2(newX, newY);
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
