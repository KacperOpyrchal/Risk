package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kacper Opyrchal on 09.02.2017.
 */

public class Menu {

    Button startBtn;

    private boolean isStartActive;

    public boolean isStartActive() {
        return isStartActive;
    }

    public void setStartActive(boolean startActive) {
        isStartActive = startActive;
    }

    public Menu(){
        isStartActive = true;
        startBtn = new Button(new Texture("mbtn2.png"), 200, 500, 200, 200);
    }

    public void renderMenu(SpriteBatch batch) {

        startBtn.update(batch);

        startBtn.startAnimation(batch);

    }




}
