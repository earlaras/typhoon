package com.typhoon.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.typhoon.handlers.GameStateManager;
import com.typhoon.game.Game;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected Game game;
	
	protected SpriteBatch batch;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	
	protected GameState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		game = gsm.game();
		batch = game.getBatch();
		cam = game.getCam();
		hudCam = game.getHudCam();
		
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
}
