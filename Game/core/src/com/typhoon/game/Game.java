package com.typhoon.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.typhoon.handlers.GameStateManager;
import com.typhoon.handlers.MyInput;
import com.typhoon.handlers.Content;

public class Game extends ApplicationAdapter {
	
	public static final String TITLE = "TYPHOON";
	public static final int vWIDTH = 455;
	public static final int vHEIGHT = 256;
	public static final int SCALE = 3;
	public static final float STEP = 1/60f;
	
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;	
	private GameStateManager gsm;
	
	public static Content content;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, vWIDTH, vHEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, vWIDTH, vHEIGHT);
		gsm = new GameStateManager(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		
		Gdx.graphics.setTitle(TITLE + "-- FPS: " + Gdx.graphics.getFramesPerSecond());

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		MyInput.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}
}
