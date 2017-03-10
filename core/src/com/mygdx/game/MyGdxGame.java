package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class
MyGdxGame extends ApplicationAdapter implements InputProcessor{
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;

	Board board;

	Menu menu;

	int xSize;
	int ySize;

	@Override
	public void create() {
		PixMaps.generatePixMaps();

		xSize = 20;
		ySize = (int)(xSize * 6/Math.sqrt(3.0));
		board = new Board(xSize, ySize);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		menu = new Menu();

		RenderBoard.init();
		Gdx.input.setInputProcessor(this);
	}
	@Override
	public void resize(int width, int height) {
	}
	@Override
	public void render() {
		Gdx.gl.glClearColor( 234.0f/255.0f, 230.0f/255.0f, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		if(menu.isShowBoard()) {
			board.drawBoard();
		}

		batch.begin();
		menu.renderMenu(batch);
		batch.end();



	}

	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
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

		Gdx.app.log("KAP", "I:O x = " + screenX + " y = " + screenY);

		menu.checkOnClick(screenX, Gdx.graphics.getHeight() - screenY);

		if(menu.renderBtn.checkIfClicked((float) screenX, Gdx.graphics.getHeight() - screenY)){
			Gdx.app.log("KAP", "HEJ");
			board = new Board(xSize, (int)(xSize * 6/Math.sqrt(3.0)));
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
