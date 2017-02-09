package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kacper Opyrchal on 04.02.2017.
 */

public class Board {

    int xSize;
    int ySize;

    List<List<Cell>> cells;

    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

    }

    public int getX(){
        return xSize;
    }

    public int getY(){
        return ySize;
    }
}
