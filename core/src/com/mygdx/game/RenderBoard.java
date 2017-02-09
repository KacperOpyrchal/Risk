package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by JavaDev on 05.11.2016.
 */
public class RenderBoard {

    static BitmapFont font;

    public static void init(){
        font = new BitmapFont();
    }

    public static void renderGrid(ShapeRenderer shapeRenderer, int xSize, int ySize){

        int x = Gdx.graphics.getWidth();
        int y = Gdx.graphics.getHeight();

        float nx = x / xSize;
        float ny = y /ySize;
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(1, 1, 0.30f, 0);

        for(float i = 0; i <= ySize; ++i){
            for(float j = 0; j <= xSize; ++j){
                renderHexagon(shapeRenderer, j * nx, i * ny, (nx / (float) Math.sqrt(3)) );
            }

            i++;

            for(float j = 0; j <= xSize; ++j){
                renderHexagon(shapeRenderer, (j * nx) + (((nx / (float) Math.sqrt(3)) * (float) Math.sqrt(3))/2), i * ny, (nx / (float) Math.sqrt(3)) );
            }



        }

        /*for(int i = 0; i <= ny; ++i){
            shapeRenderer.rectLine(0, i*(y/ny), x, i*(y/ny), 1);
        }
        for(int i = 0; i <= nx; ++i){
            shapeRenderer.rectLine(i*(x/nx), 0, i*(x/nx), y, 1);
        }*/

        //shapeRenderer.end();

    }

    public static void renderHexagon(ShapeRenderer shapeRenderer, float x, float y, float size){

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);

        shapeRenderer.rectLine(x, y, x - ((size * (float) Math.sqrt(3))/2) ,  y-(size/2), 1);
        shapeRenderer.rectLine(x, y, x + ((size * (float) Math.sqrt(3))/2) ,  y-(size/2), 1);

        x = x - ((size * (float) Math.sqrt(3))/2);
        y = y - (size/2);

        shapeRenderer.rectLine(x, y, x ,  y - size, 1);
        shapeRenderer.rectLine(x + (size * (float) Math.sqrt(3)) , y, x + (size * (float) Math.sqrt(3)) ,  y - size, 1);

        y -= size;

        shapeRenderer.rectLine(x  + (size * (float) Math.sqrt(3)) , y, x - ((size * (float) Math.sqrt(3))/2) + (size * (float) Math.sqrt(3))  ,  y-(size/2), 1);
        shapeRenderer.rectLine(x, y, x + ((size * (float) Math.sqrt(3))/2) ,  y-(size/2), 1);


        shapeRenderer.end();

    }




}
