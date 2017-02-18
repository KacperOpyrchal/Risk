package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kacper Opyrchal on 09.02.2017.
 */

public class Menu  implements InputProcessor {

    Button startBtn;

    private boolean isStartActive;

    public boolean isStartActive() {
        return isStartActive;
    }

    public void setStartActive(boolean startActive) {
        isStartActive = startActive;
    }

    public Menu(){
        Gdx.input.setInputProcessor(this);
        isStartActive = true;
        startBtn = new Button(new Texture("mbtn2.png"), 200, 500, 200, 200);
    }

    public void renderMenu(SpriteBatch batch) {


        startBtn.update(batch);

        startBtn.startAnimation(batch);

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        if(this.startBtn.checkIfClicked(screenX, screenY)){
            this.setStartActive(!this.isStartActive());
        }

        if(this.isStartActive()){
            this.startBtn.skin.setTexture(new Texture("mbtn2.png"));
        }else{
            this.startBtn.skin.setTexture(new Texture("mbtn1.png"));
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



}
