package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Created by Kacper Opyrchal on 09.02.2017.
 */

public class Menu {
    private Texture texture;

    private Sound clickSound;
    private Sound startSound;
    private Sound renderSound;
    private Sound backSound;

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

        //Random random = new Random();

        texture = new Texture("abstractbackgrounds/20.png");

        clickSound = Gdx.audio.newSound(Gdx.files.internal("menu/audio/click1.ogg"));
        renderSound = Gdx.audio.newSound(Gdx.files.internal("menu/audio/load.wav"));
        startSound = Gdx.audio.newSound(Gdx.files.internal("menu/audio/select.wav"));
        backSound = Gdx.audio.newSound(Gdx.files.internal("menu/audio/back.wav"));

        isShowBoard = false;
        isStartActive = true;

        /*
        int xSize = Gdx.graphics.getWidth();
        int ySize = xSize/5;

        int xPosition = 0;
        int yPosition = Gdx.graphics.getHeight()/4 - ySize;

        startBtn = new Button(new Texture("menu/button_story-mode.png"), xPosition, yPosition*4, xSize, ySize, startSound);
        quickGameBtn = new Button(new Texture("menu/button_quick-game.png"), xPosition, yPosition*3, xSize, ySize, clickSound);
        settingsBtn = new Button(new Texture("menu/button_options.png"), xPosition, yPosition*2, xSize, ySize, clickSound);
        exitBtn = new Button(new Texture("menu/button_exit.png"), xPosition, yPosition*1, xSize, ySize, clickSound);
        */

        float xSize = Gdx.graphics.getWidth()/2;
        float ySize = xSize * (3.0f/4.0f);

        float xPosition = Gdx.graphics.getWidth()/4;
        float yPosition = Gdx.graphics.getHeight() - ySize - (ySize/4);

        startBtn = new Button(new Texture("menu/button_story-mode.png"), xPosition, yPosition, xSize, ySize, startSound);
        quickGameBtn = new Button(new Texture("menu/button_quick-game.png"), xPosition, yPosition - ySize, xSize, ySize, clickSound);
        settingsBtn = new Button(new Texture("menu/button_options.png"), xPosition, yPosition - ySize*2, xSize, ySize, clickSound);
        exitBtn = new Button(new Texture("menu/button_exit.png"), xPosition, yPosition - ySize*3, xSize, ySize, clickSound);

        backBtn = new Button(new Texture("menu/back.png"), xPosition, (5*Gdx.graphics.getHeight())/6 , Gdx.graphics.getHeight()/6, Gdx.graphics.getHeight()/6, backSound);

        renderBtn = new Button(new Texture("menu/restart.png"), xPosition + Gdx.graphics.getHeight()/6, (5*Gdx.graphics.getHeight())/6, Gdx.graphics.getHeight()/6, Gdx.graphics.getHeight()/6, renderSound);
    }

    public void renderMenu(SpriteBatch batch){

        if(isShowBoard){
            renderBtn.update(batch);
            backBtn.update(batch);

        }else{

            //batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
                return;
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
                return;
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
