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

    public static void renderHexBoard(Board board){
        board.drawBoard();
    }




}
