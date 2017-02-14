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
        createBoard();
        createBoardV2();
    }


    private void createBoardV2(){
        Color color = Color.BLACK;
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            /*switch(i){
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
            }*/
            int xCenter = random.nextInt(xSize);
            int yCenter = random.nextInt(ySize);

            Vector2 Center = new Vector2(xCenter, yCenter);

            createCity(Center,4, 2, color);
        }

        color = Color.BLACK;
        createBoardAddEmptySpace(color);
    }

    private void createBoardAddEmptySpace(Color color){
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                if(!citiesBoolean[i][j])
                    cells[i][j] = new Cell(i, j, a, color);
            }
        }
    }


    private void createBoard(){

        for(int i = 0; i < ySize; ++i){
            for(int j = 0; j < xSize; ++j){

                citiesBoolean[j][i] = false;

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

                //color = Color.GOLD;

                cells[j][i] = new Cell(j, i, a, color);
            }
        }

        //Color color = Color.BLACK;

        //cells[xSize-5][ySize-3] = new Cell(xSize-5, ySize-3, a, color);
    }



    // Punkt D = (xD, yD) to punkt do sprawdzenia czy się zawiera w figurze, Center to punkt ktory został wylosowany i figura jest tworzona na jego podstawie
    private boolean linearInequation(Vector2 A, Vector2 B, Vector2 Center, Vector2 D){  // A = (xA,yB), B = ...
        float xA = A.x;
        float yA = A.y;
        float xB = B.x;
        float yB = B.y;
        float xCenter = Center.x;
        float yCenter = Center.y;
        float xD = D.x;
        float yD = D.y;

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


    private void createRandomFigure(Vector2 Center, int radius, int n, Vector2[] vertices){ // figura jest tworzona na podstawie koła ///  n - ilość wierzchołków
        float xCenter = Center.x;
        float yCenter = Center.y;

        int angle = 360 / n; // koło jest dzielone na fragmenty

        Random random = new Random();

        for(int i = 0; i < n; i++){

            int alpha = random.nextInt(angle); // losowany jest kąt względem ktorego wyliczany jest punkt
            int distance = random.nextInt(radius + 1); // losowana odleglość od punktu Center

            double radianAngle = Math.toRadians(i * angle + alpha);

            double x = Math.sin(radianAngle) * distance;
            double y = Math.cos(radianAngle) * distance;

            float newX = (float)(xCenter + x);
            float newY = (float)(yCenter + y);

            vertices[i] = new Vector2(newX, newY);
        }
    }


    private boolean inFigure(Vector2 Center, Vector2[] vertices, Vector2 D, int n){

        boolean in = true; // czy jest w figurze

        for(int i = 0, j = 1; i < n; i++, j++){
            j = j % n;
            in = linearInequation(vertices[i], vertices[j], Center, D);
            if(!in) return in;
        }

        return in;
    }

    private int xLeftLimit(float x, int radius){ // funkcja zwraca pierwszego x ktory zawiera się w xCenter - Radius
        int xLim = (int)(x) - radius;
        if(xLim < 0) xLim = 0;

        return xLim;
    }

    private int xRightLimit(float x, int radius){
        int xLim = (int)(x) + radius;
        if(xLim >= xSize) xLim = xSize - 1;

        return xLim;
    }

    private int yTopLimit(float y, int radius){
        int yLim = (int)(y) - radius;
        if(yLim < 0) yLim = 0;

        return yLim;
    }

    private int yLowerLimit(float y, int radius){
        int yLim = (int)(y) + radius;
        if(yLim >= ySize) yLim = ySize - 1;

        return yLim;
    }

    private boolean isOccupied(int x, int y){
        if(citiesBoolean[x][y])
            return true;
        else return false;
    }


    private void createCity(Vector2 Center, int n, int radiusRand, Color color){

        Vector2[] vertices = new Vector2[n];

        for(int i = 0; i < n; ++i){
            vertices[i] =  new Vector2(0, 0);
        }

        Random random = new Random();
        int radius = random.nextInt(radiusRand - 1) + 1;

        createRandomFigure(Center, n, radius, vertices);

        Vector2 D = new Vector2();

        for(int i = xLeftLimit(Center.x, radius); i <= xRightLimit(Center.x, radius); i++){
            for(int j = yTopLimit(Center.y, radius); j <= yLowerLimit(Center.y, radius); j++){

                D.x = i; D.y = j;

                if(isOccupied(i, j) == false && inFigure(Center, vertices, D, n) == true)
                    cells[i][j] = new Cell(i, j, a, color);
                    citiesBoolean[i][j] = true;
            }
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
