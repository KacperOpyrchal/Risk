package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import java.util.Random;

public class
MyGdxGame extends ApplicationAdapter implements InputProcessor{
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;

	Texture img;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	Stage stage;
	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;

	Cell testCell;
	Board board;

	Menu menu;

	@Override
	public void create() {
		int xSize = 20;
		board = new Board(xSize, (int)(xSize * 6/Math.sqrt(3.0)));

		testCell = new Cell(100, 100, 100, Color.GREEN);
		//testCell.setTextureHexagon(Color.GREEN);

		//initButton();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		menu = new Menu();

		///*

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.update();
		tiledMap = new TmxMapLoader().load("test.tmx");

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		//*/

		RenderBoard.init();
		Gdx.input.setInputProcessor(this);
	}
	@Override
	public void resize(int width, int height) {}
	@Override
	public void render() {
		Gdx.gl.glClearColor( 1, 1, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		board.drawBoard();

		/*batch.begin();
		menu.renderMenu(batch);
		batch.end();
		*/

		/*if(menu.isStartActive()) {
			RenderBoard.renderGrid(shapeRenderer, 9, 16);
		}

		RenderBoard.renderHexagon(shapeRenderer, 200, 500, 150);

		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		camera.translate(1,1);*/

		//testCell.drawHexagon();



	}

	public void initButton(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("up-button");
		textButtonStyle.down = skin.getDrawable("down-button");
		textButtonStyle.checked = skin.getDrawable("checked-button");
		button = new TextButton("Button1", textButtonStyle);
		stage.addActor(button);
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
		//Random rand = new Random();
		camera.translate(-100,-100);

		//menu.setStartActive(!menu.isStartActive());

		if(!menu.startBtn.checkIfClicked(screenX, screenY)){
			menu.setStartActive(!menu.isStartActive());
		}

		if(menu.isStartActive()){
			menu.startBtn.skin.setTexture(new Texture("mbtn2.png"));
		}else{
			menu.startBtn.skin.setTexture(new Texture("mbtn1.png"));
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
