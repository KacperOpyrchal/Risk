package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
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

    public ShapeOfCity(int xSize, int ySize, Cell[][] cells, boolean[][] citiesBoolean, List<Cell> cellsCity, Color color, float a, int radius){
        this.xSize = xSize;
        this.ySize = ySize;
        this.a = a;

        this.cells = cells;
        this.citiesBoolean = citiesBoolean;
        this.cellsCity = cellsCity;

        Random random = new Random();

        float x = (float)(random.nextInt(xSize));
        float y = (float)(random.nextInt(ySize));

        Vector2 Center = new Vector2(x,y);

        int n = random.nextInt(4) + 3;

        createCity(Center, n, radius, color);
    }

    private boolean linearInequation(Vector2 A, Vector2 B, Vector2 Center, Vector2 D){

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

    private void createRandomFigure(Vector2 Center, int radius, int n, Vector2[] vertices){ // figura jest tworzona na podstawie koła ///  n - ilość wierzchołków
        float xCenter = Center.x;
        float yCenter = Center.y;

        int angle = 360 / n; // koło jest dzielone na fragmenty

        Random random = new Random();

        for(int i = 0; i < n; i++){

            int alpha = random.nextInt(angle); // losowany jest kąt względem ktorego wyliczany jest punkt
            int distance = random.nextInt(radius) + 2; // losowana odleglość od punktu Center

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
        int yLim = (int)(y) - radius;
        if(yLim < 0) yLim = 0;

        return yLim;
    }

    private int yLowerLimit(float y, int radius){
        int yLim = (int)(y) + radius;
        if(yLim >= ySize) yLim = ySize - 1;

        return yLim;
    }

    private boolean isOccupied(int x, int y){ // wiem że to można zrobić w ifie !citiesBoolean[x][y] ale tak będzie czytelniej
        if(citiesBoolean[x][y])
            return true;
        return false;
    }

    private void createCity(Vector2 Center, int n, int radiusRand, Color color){

        Vector2[] vertices = new Vector2[n];

        Random random = new Random();
        int radius = random.nextInt(radiusRand - 1) + 1;

        createRandomFigure(Center, radius, n, vertices);

        Vector2 D = new Vector2();

        for(int i = xLeftLimit(Center.x, radius); i <= xRightLimit(Center.x, radius); i++){
            for(int j = yTopLimit(Center.y, radius); j <= yLowerLimit(Center.y, radius); j++){

                D.x = i; D.y = j;

                if(!isOccupied(i, j) && inFigure(Center, vertices, D, n)){
                    cells[i][j] = new Cell(i, j, a, color);
                    citiesBoolean[i][j] = true;
                    cellsCity.add(cells[i][j]);
                }
            }
        }
    }

}
