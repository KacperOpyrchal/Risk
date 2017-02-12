package com.mygdx.game;

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

    Cell[][] board;

    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        board = new Cell[xSize][ySize];
    }

    private void createBoard(){
        for(int i = 0; i < xSize; ++i){
            for(int j = 0; j < xSize; ++j){
                // Dodawanie cell do tablicy board...
                // funkcja do tworzenia panstw...
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
