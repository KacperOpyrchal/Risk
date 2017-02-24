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
    Button backBtn;

    Button renderBtn;

    private boolean isStartActive;
    private boolean isShowBoard;

    public boolean isStartActive() {
        return isStartActive;
    }

    public void setStartActive(boolean startActive) {
        isStartActive = startActive;
    }

    public Menu(){

        isShowBoard = false;
        isStartActive = true;

        int xSize = Gdx.graphics.getWidth();
        int ySize = xSize/10;

        int xPosition = 0;
        int yPosition = Gdx.graphics.getHeight()/4 - ySize;


        startBtn = new Button(new Texture("menu/button_story-mode.png"), xPosition, yPosition*4, xSize, ySize);
        quickGameBtn = new Button(new Texture("menu/button_quick-game.png"), xPosition, yPosition*3, xSize, ySize);
        settingsBtn = new Button(new Texture("menu/button_options.png"), xPosition, yPosition*2, xSize, ySize);
        exitBtn = new Button(new Texture("menu/button_exit.png"), xPosition, yPosition*1, xSize, ySize);

        backBtn = new Button(new Texture("menu/button_back.png"), xPosition, (5*Gdx.graphics.getHeight())/6 , Gdx.graphics.getHeight()/6, Gdx.graphics.getHeight()/6);

        renderBtn = new Button(new Texture("menu/button_render.png"), xPosition + Gdx.graphics.getHeight()/6, (5*Gdx.graphics.getHeight())/6, Gdx.graphics.getHeight()/6, Gdx.graphics.getHeight()/6);
    }

    public void renderMenu(SpriteBatch batch){

        if(isShowBoard){
            renderBtn.update(batch);
            backBtn.update(batch);

        }else{
            startBtn.update(batch);
            quickGameBtn.update(batch);
            settingsBtn.update(batch);
            exitBtn.update(batch);

        }

    }

    public void checkOnClick(int x, int y){

        if(startBtn.checkIfClicked(x, y)){
            if(!isShowBoard){
                isShowBoard = true;
            }
        }


        if(quickGameBtn.checkIfClicked(x, y)){

        }

        if(settingsBtn.checkIfClicked(x, y)){

        }

        if(exitBtn.checkIfClicked(x, y)){

        }

        if(renderBtn.checkIfClicked(x, y)){

        }

        if(backBtn.checkIfClicked(x, y)){
            if(isShowBoard){
                isShowBoard = false;
            }
        }

    }


    public boolean isShowBoard() {
        return isShowBoard;
    }

    public void setShowBoard(boolean showBoard) {
        isShowBoard = showBoard;
    }
}
