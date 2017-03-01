package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
//import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper Opyrchal on 04.02.2017
 * Updated by Marcin Holota on 10.02.2017
 * Updated by Kacper Opyrchal on 10.02.2017
 *  1) Draw method has been added
 *  2) Finding method has been improved
 * Constructor and function findNeighbours have been changed by Marcin Holota on 25.02.2017.
 */

public class Cell {

    float x;
    float y;
    float realX;
    float realY;
    float size;
    Texture textureHexagon;
    PolygonRegion polygonRegion;

    boolean[] borders;

    Board board;

    List<Cell> neighbours;
    boolean[] neighboursBoolean;

    public Cell(float x, float y, float size, Pixmap pixmap, Board board){
        this.x = x;
        this.y = y;
        this.size = size;
        setRealX();
        setRealY();
        setTextureHexagon(pixmap);
        setShape(size);

        this.board = board;

        neighbours = new ArrayList<Cell>();

        borders = new boolean[6];
        neighboursBoolean = new boolean[6]; // true jak jest sąsiad
    }

    private void setRealX(){
        realX = x * 3 * size + size;
        if(y%2 == 1) realX += (1.5 * size);
    }

    private void setRealY(){
        realY = (float) ((y+1) * Math.sqrt(3)) * size / 2;
    }

    private boolean validate(Cell neighbour, int width, int height){
        boolean correct = true;

        if(neighbour == this) // warunek który jest opisany w ! topNeighbour() !
            correct = false;

        else if(!board.inMap[(int)neighbour.x][(int)neighbour.y])
            correct = false;

        else if(neighbour.x < 0 || neighbour.x >= width)
            correct = false;

        else if(neighbour.y < 0 || neighbour.y >= height)
            correct = false;

        return correct;
    }

    public Cell topNeighbour(){
        Cell top = this; // to na wypadek jakby miałbyć nullptr
        // w validate jest warunek do obsługi tego przypadku

        if(y - 2 >= 0)
            top = board.cells[(int)x][(int)y-2];

        return top;
    }

    public Cell topLeftNeighbour(){
        Cell topLeft = this;

        if(x - y%2 >= 0 && y - 1 >= 0)
            topLeft = board.cells[(int)x-(int)y%2][(int)y-1];

        return topLeft;
    }

    public Cell topRightNeighbour(){
        Cell topRight = this;

        if(x + 1 - y%2 < board.xSize && y - 1 >= 0)
            topRight = board.cells[(int)x+1-(int)y%2][(int)y-1];

        return topRight;
    }

    public Cell bottomNeighbour(){
        Cell bottom = this;

        if(y + 2 < board.ySize)
            bottom = board.cells[(int)x][(int)y+2];

        return bottom;
    }

    public Cell bottomLeftNeighbour(){
        Cell bottomLeft = this;

        if(x - y%2 >= 0 && y + 1 < board.ySize)
            bottomLeft = board.cells[(int)x-(int)y%2][(int)y+1];

        return bottomLeft;
    }

    public Cell bottomRightNeighbour(){
        Cell bottomRight = this;

        if(x + 1 - y%2 < board.xSize && y + 1 <board.ySize)
            bottomRight = board.cells[(int)x+1-(int)y%2][(int)y+1];

        return bottomRight;
    }

    public void findNeighbours(){
        Cell newNeighbour = topNeighbour();
        if(validate(newNeighbour, board.getX(), board.getY())){
            neighbours.add(board.cells[(int)newNeighbour.x][(int)newNeighbour.y]);
            neighboursBoolean[0] = true;
        }

        newNeighbour = topRightNeighbour();
        if(validate(newNeighbour,  board.getX(), board.getY())){
            neighbours.add(board.cells[(int)newNeighbour.x][(int)newNeighbour.y]);
            neighboursBoolean[1] = true;
        }

        newNeighbour = bottomRightNeighbour();
        if(validate(newNeighbour,  board.getX(), board.getY())){
            neighbours.add(board.cells[(int)newNeighbour.x][(int)newNeighbour.y]);
            neighboursBoolean[2] = true;
        }

        newNeighbour = bottomNeighbour();
        if(validate(newNeighbour,  board.getX(), board.getY())){
            neighbours.add(board.cells[(int)newNeighbour.x][(int)newNeighbour.y]);
            neighboursBoolean[3] = true;
        }

        newNeighbour = bottomLeftNeighbour();
        if(validate(newNeighbour,  board.getX(), board.getY())){
            neighbours.add(board.cells[(int)newNeighbour.x][(int)newNeighbour.y]);
            neighboursBoolean[4] = true;
        }

        newNeighbour = topLeftNeighbour();
        if(validate(newNeighbour, board.getX(), board.getY())){
            neighbours.add(board.cells[(int)newNeighbour.x][(int)newNeighbour.y]);
            neighboursBoolean[5] = true;
        }

    } // ta funkcja ustawia wskaźniki na sąsiadów zamiast tworzyć nowe obiekty

    public void setTextureHexagon(Pixmap pixMap){
        textureHexagon = new Texture(pixMap);
    }

    private void setShape(float size){
        float sqrt3 = (float)Math.sqrt(3)/2;
        float[] vertices = new float[]{
            -size, 0,
            -size/2, - size * sqrt3,
            size/2, - size * sqrt3,
            size, 0,
            size/2, size * sqrt3,
            -size/2, size * sqrt3
        };

        short[] triangles = new short[]{
            0, 2, 1,
            0, 3, 2,
            0, 4, 3,
            0, 5, 4
        }; // nie pytajcie czemu to jest... musi być, ale jest do niczego nie potrzebne...

        polygonRegion = new PolygonRegion(new TextureRegion(textureHexagon), vertices, triangles);
    }


}
