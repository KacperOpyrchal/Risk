package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kacper Opyrchal on 09.02.2017.
 */

public class Menu {

    Button startBtn;
    Button quickGameBtn;
    Button settingsBtn;
    Button exitBtn;

    Button renderBtn;
    private boolean isStartActive;

    public boolean isStartActive() {
        return isStartActive;
    }

    public void setStartActive(boolean startActive) {
        isStartActive = startActive;
    }

    public Menu(){
        isStartActive = true;
        startBtn = new Button(new Texture("mbtn2.png"), 0, Gdx.graphics.getHeight()-200, 100, 100);
        quickGameBtn = new Button(new Texture("mbtn2.png"), 200, Gdx.graphics.getHeight()-200, 100, 100);
        settingsBtn = new Button(new Texture("mbtn2.png"), 400, Gdx.graphics.getHeight()-200, 100, 100);
        exitBtn = new Button(new Texture("mbtn2.png"), 600, Gdx.graphics.getHeight()-200, 100, 100);

        renderBtn = new Button(new Texture("render.png"), (Gdx.graphics.getWidth()/2)-100, Gdx.graphics.getHeight()-200, 100, 100);
    }

    public void renderMenu(SpriteBatch batch){

        renderBtn.update(batch);

        /*
        startBtn.update(batch);
        quickGameBtn.update(batch);
        settingsBtn.update(batch);
        exitBtn.update(batch);
        */
    }


}
