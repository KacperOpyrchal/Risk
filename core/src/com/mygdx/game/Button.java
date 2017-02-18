package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.RenderBoard.font;

/**
 * Created by Kacper Opyrchal on 09.02.2017.
 * Updated by Michał Bednarz on 14.02.2017.
 */

public class Button {

    public Sprite skin;

    public Button(Texture texture, float x, float y, float width, float height) {
        skin = new Sprite(texture);
        skin.setPosition(x, y);
        skin.setSize(width, height);
    }

    public void update(SpriteBatch batch) {
        //checkIfClicked(input_x, input_y);
        skin.draw(batch);
        font.draw(batch,"Hello World", 300, 300);

    }

    public void startAnimation(SpriteBatch batch) {
       // skin.rotate(20);

    }

    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                return true;
            }
        }

        return false;
    }

}