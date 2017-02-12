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

import java.util.List;

/**
 * Created by Kacper Opyrchal on 04.02.2017
 * Updated by Marcin Holota on 10.02.2017
 * Updated by Kacper Opyrchal on 10.02.2017
 *  1) Draw method has been added
 */

public class Cell {

    float x;
    float y;
    float size;
    Texture textureHexagon;
    PolygonSprite hexagon;
    PolygonSpriteBatch hexagonBatch;

    List<Cell> neighbours;

    public Cell(float x, float y, float size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        setTextureHexagon(color);
        setShape(size);
    }

    private boolean validate(Cell neighbour, int width, int height){
        boolean correct = true;
        if(neighbour.x < 0 || neighbour.x >= width)
            correct = false;
        if(neighbour.y < 0 || neighbour.y >= height)
            correct = false;
        return correct;
    }

    private Cell topNeighbour(){
        Cell top = new Cell(x, y - 2, size, Color.GREEN);
        return top;
    }

    private Cell topLeftNeighbour(){
        Cell topLeft = new Cell(x - y%2, y - 1, size, Color.GREEN);
        return topLeft;
    }

    private Cell topRightNeighbour(){
        Cell topRight = new Cell(x + 1 - y%2, y - 1, size, Color.GREEN);
        return topRight;
    }

    private Cell bottomNeighbour(){
        Cell bottom = new Cell(x, y + 2, size, Color.GREEN);
        return bottom;
    }

    private Cell bottomLeftNeighbour(){
        Cell bottomLeft = new Cell(x - y%2, y + 1, size, Color.GREEN);
        return bottomLeft;
    }

    private Cell bottomRightNeighbour(){
        Cell bottomRight = new Cell(x + 1 - y%2, y + 1, size, Color.GREEN);
        return bottomRight;
    }

    public void findNeighbours(int width, int height){
        Cell newNeighbour = topNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = topLeftNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = topRightNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = bottomNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = bottomLeftNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);

        newNeighbour = bottomRightNeighbour();
        if(validate(newNeighbour, width, height))
            neighbours.add(newNeighbour);
    }

    public void setTextureHexagon(Color textureColor){ // kolory typu BLACK, RED...
        Pixmap pixMap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixMap.setColor(textureColor);
        pixMap.fill();
        textureHexagon = new Texture(pixMap);
    } // ta funkcja powinna być wywoływana w klasie miasto, aby ustawić dobry kolor...
    // chodzi o to do jakiego państwa należy

    private void setShape(float size){
        float[] vertices = new float[]{
            -size, 0,
            -size/2, - size,
            size/2, - size,
            size, 0,
            size/2, size,
            -size/2, size
        };

        short[] triangles = new short[]{
            0, 2, 1,
            0, 3, 2,
            0, 4, 3,
            0, 5, 4
        }; // nie pytajcie czemu to jest... musi być, ale jest do niczego nie potrzebne...

        PolygonRegion polygonRegion = new PolygonRegion(new TextureRegion(textureHexagon), vertices, triangles);

        hexagon = new PolygonSprite(polygonRegion);
        hexagon.setOrigin(0f, 0f);
        hexagonBatch = new PolygonSpriteBatch();
    }

    public void drawHexagon(){

        hexagonBatch.begin();

        hexagon.setX(x);
        hexagon.setY(y);
        hexagon.draw(hexagonBatch);

        hexagonBatch.end();

    }


}
