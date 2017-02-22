package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by Kacper Opyrchal on 2/22/2017.
 */

public class PixMaps {

    public static Pixmap pixMapOne;
    public static Pixmap pixMapTwo;
    public static Pixmap pixMapThree;
    public static Pixmap pixMapFour;
    public static Pixmap pixMapFive;
    public static Pixmap pixMapSix;

    public static void generatePixMaps() {
        pixMapOne = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixMapTwo = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixMapThree = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixMapFour = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixMapFive = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixMapSix = new Pixmap(1,1, Pixmap.Format.RGBA8888);

        pixMapOne.setColor(Color.BLUE);
        pixMapOne.fill();

        pixMapTwo.setColor(Color.RED);
        pixMapTwo.fill();

        pixMapThree.setColor(Color.GREEN);
        pixMapThree.fill();

        pixMapFour.setColor(Color.YELLOW);
        pixMapFour.fill();

        pixMapFive.setColor(Color.ORANGE);
        pixMapFive.fill();

        pixMapSix.setColor(Color.ORANGE);
        pixMapSix.fill();

    }
}
