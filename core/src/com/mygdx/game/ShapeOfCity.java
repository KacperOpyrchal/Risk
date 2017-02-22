package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Holota on 2017-02-17.
 */

public class ShapeOfCity {

    int xSize;
    int ySize;
    float a;

    boolean[][] citiesBoolean;
    Cell[][] cells;
    List<Cell> cellsCity;
    boolean[][] inMap;

    public ShapeOfCity(Board board, List<Cell> cellsCity, Pixmap pixmap, int radius){
        this.xSize = board.xSize;
        this.ySize = board.ySize;
        this.a = board.a;

        this.cells = board.cells;
        this.citiesBoolean = board.citiesBoolean;
        this.cellsCity = cellsCity;

        this.inMap = board.inMap;

        Random random = new Random();

        float x = (float)(random.nextInt(xSize));
        float y = (float)(random.nextInt(ySize));

        Vector2 Center = new Vector2(x,y);

        int n = random.nextInt(4) + 6;

        createCity(Center, n, radius, pixmap);
    }

    public ShapeOfCity(Board board, int radius){
        this.xSize = board.xSize;
        this.ySize = board.ySize;
        this.a = board.a;

        this.cells = board.cells;
        this.citiesBoolean = board.citiesBoolean;
        this.inMap = board.inMap;

        Random random = new Random();

        float x = (float)(random.nextInt(xSize));
        float y = (float)(random.nextInt(ySize));

        Vector2 Center = new Vector2(xSize / 2, ySize / 2);

        int n = random.nextInt(6) + 11;

        createMap(Center, n, radius);
    }



    private void createRandomFigure(Vector2 Center, int radius, int n, Vector2[] vertices, int minLength){ // figura jest tworzona na podstawie koła ///  n - ilość wierzchołków

        int angle = 360 / n; // koło jest dzielone na fragmenty

        Random random = new Random();

        for(int i = 0; i < n; i++){

            int alpha = random.nextInt(angle); // losowany jest kąt względem ktorego wyliczany jest punkt
            int distance = random.nextInt(radius) + minLength; // losowana odleglość od punktu Center

            double radianAngle = Math.toRadians(i * angle + alpha);

            double x = Math.sin(radianAngle) * distance;
            double y = Math.cos(radianAngle) * distance;

            float newX = (float)(Center.x + x);
            float newY = (float)( (Center.y + y * 3.46410161514));

            vertices[i] = new Vector2(newX, newY);
        }
    }

    private boolean inFigure(Vector2 Center, Vector2[] vertices, Vector2 D, int n){
        boolean in = true; // czy jest w figurze

        for(int i = 0, j = 1; i < n; i++, j++){
            j = j % n;
            in = linearInequation(vertices[i], vertices[j], Center, D);
            if(!in) break;
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
        int yLim = (int)(y) - 3*radius;
        if(yLim < 0) yLim = 0;

        return yLim;
    }

    private int yLowerLimit(float y, int radius){
        int yLim = (int)(y) + 3*radius;
        if(yLim >= ySize) yLim = ySize - 1;

        return yLim;
    }

    private boolean isOccupied(int x, int y){ // wiem że to można zrobić w ifie !citiesBoolean[x][y] ale tak będzie czytelniej
        return citiesBoolean[x][y]; // tak chyba będzie jeszcze czytelniej :P ~Kacper
    }

    private void createCity(Vector2 Center, int n, int radiusRand, Pixmap pixmap){

        Vector2[] vertices = new Vector2[n];

        float[][] linearIneq = new float[n][3];


        Random random = new Random();
        int radius = random.nextInt(radiusRand - 1) + 1;

        createRandomFigure(Center, radius, n, vertices, 2);

        linearInq(vertices, linearIneq, n, Center);

        Vector2 D = new Vector2();

        int rightLimit = xRightLimit(Center.x, radius);
        int lowerLimit = yLowerLimit(Center.y, radius);

        for(int i = xLeftLimit(Center.x, radius); i <= rightLimit ; i++){
            for(int j = yTopLimit(Center.y, radius); j <= lowerLimit; j++){

                D.x = i; D.y = j;

                if((!citiesBoolean[i][j] && inMap[i][j]) && ((distance(D,Center,2)) || (inFigureV2(linearIneq, n, D)))){
                    cells[i][j] = new Cell(i, j, a, pixmap);
                    citiesBoolean[i][j] = true;
                    cellsCity.add(cells[i][j]);
                }
            }
        }
    }

    private void linearInq(Vector2[] vertices, float[][] linearIneq, int n, Vector2 Center){
        for(int i = 0, j = 1; i < n; i++, j++){
            j %= n;

            if(vertices[i].x - vertices[j].x == 0) vertices[i].x += 0.1;

            float a = (vertices[i].y - vertices[j].y)/(vertices[i].x - vertices[j].x);
            float b = vertices[i].y - a * vertices[i].x;

            float xCenterResult = a * Center.x + b;

            linearIneq[i][0] = a;
            linearIneq[i][1] = b;

            if(xCenterResult <= Center.y)
                linearIneq[i][2] = 1; // oznacza że punkt musi być ponad prostą
            else
                linearIneq[i][2] = -1; // oznacza poniżej osi
        }
    }

    private boolean inFigureV2(float[][] linearIneq, int n, Vector2 Point){
        float resultForPoint;

        for(int i = 0; i < n; i++){
            resultForPoint = linearIneq[i][0] * Point.x + linearIneq[i][1];

            if(linearIneq[i][2] == 1){
                if(resultForPoint > Point.y)
                    return false;
            }
            else{
                if(resultForPoint < Point.y)
                    return  false;
            }
        }

        return true;
    }



    private boolean linearInequation(Vector2 A, Vector2 B, Vector2 Center, Vector2 D){
        if(A.x - B.x == 0) A.x += 0.1;
        float a = (A.y - B.y)/(A.x - B.x);
        float b = A.y - a * A.x;

        float xCenterResult = a * Center.x + b;
        float xDResult = a * D.x + b;

        if(xCenterResult <= Center.y){
            if(xDResult <= D.y)
                return true;
        } else {
            if(xDResult >= D.y)
                return true;
        }
        return false;
    }



    private void createMap(Vector2 Center, int n, int radiusRand){

        Vector2[] vertices = new Vector2[n];

        Random random = new Random();

        float[][] linearIneq = new float[n][3];

        int radius = xSize / 4;
        int minRadius = 2 * xSize / 5;
        createRandomFigure(Center, radius, n, vertices, minRadius);


        linearInq(vertices, linearIneq, n, Center);

        Vector2 D = new Vector2();

        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){

                D.x = i; D.y = j;

                if(distance(D, Center, radius) || inFigureV2(linearIneq, n, D))
                    inMap[i][j] = true;
            }
        }
    }

    private boolean distance(Vector2 D, Vector2 Center, float radius){
        float x = Center.x - D.x;
        float y = Center.y - (float)(D.y / 3.46410161514);
        if(Math.sqrt(x*x+y*y) <= radius)
            return true;
        return false;
    }

}
