package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kacper Opyrchal on 09.02.2017.
 * Updated by Michał Bednarz on 14.02.2017.
 */

public class Button {

    public Sprite skin;
    private Sound sound;

    public Button(Texture texture, float x, float y, float width, float height, Sound sound) {
        skin = new Sprite(texture);
        skin.setPosition(x, y);
        skin.setSize(width, height);
        this.sound = sound;
    }

    public void update(SpriteBatch batch) {
        skin.draw(batch);
    }

    public void startAnimation(){


    }

    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                //sound.play();
                return true;
            }
        }

        return false;
    }

}