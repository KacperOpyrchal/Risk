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

        int nx = xSize;
        int ny = ySize;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0.30f, 0);

        for(int i = 0; i <= ny; ++i){
            shapeRenderer.rectLine(0, i*(y/ny), x, i*(y/ny), 1);
        }
        for(int i = 0; i <= nx; ++i){
            shapeRenderer.rectLine(i*(x/nx), 0, i*(x/nx), y, 1);
        }

        shapeRenderer.end();

    }


}
