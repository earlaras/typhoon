package com.typhoon.states;

import static com.typhoon.handlers.Variables.PPM;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.typhoon.handlers.Variables;
import com.typhoon.entities.Enemy;
import com.typhoon.entities.HUD;
import com.typhoon.entities.Player;
import com.typhoon.entities.PowerUp;
import com.typhoon.handlers.GameStateManager;
import com.typhoon.handlers.MyContactListener;

public class Play extends GameState{
	
	private boolean debug = true; //DEBUG
	
	private World world;                                //CREATE WORLD AND B2D STUFF
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera B2DCam;
	private MyContactListener contactListener;
	
	private TiledMap tileMap;                           //CREATE TILE STUFF
	private float tileSize;
	private OrthogonalTiledMapRenderer tmRenderer;
	
	private Player player;                              //CREATE ENTITIES
	private Array<PowerUp> powerUps;
	private HUD hud;
	private Enemy snake;
	
	public Play(GameStateManager gsm) {
		
		super(gsm);
		
				//SET UP BOX2D STUFF
		
				world = new World (new Vector2(0, -9.81f), true);
				contactListener = new MyContactListener();
				world.setContactListener(contactListener);
				debugRenderer = new Box2DDebugRenderer();
				
				//SET UP ENTITIES
				createPlayer();
				
		
	}

	private void createPlayer() {
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(160/PPM,200/PPM);    
		bdef.type = BodyType.DynamicBody;      // dynamic body: always affected by forces, can move
		Body body = world.createBody(bdef);
		shape.setAsBox(8/PPM, 15/PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = Variables.bitPlayer;
		fdef.filter.maskBits = Variables.bitPlatform | Variables.bitPower | Variables.bitEnemy;
		fdef.friction = 0.5f;
		body.createFixture(fdef).setUserData("player");	
		
		//CREATE FOOT SENSOR (SENSOR == GHOST FIXTURE DETECTIN COLLISIONS)
		
		shape.setAsBox(10/PPM, 2/PPM, new Vector2(0, -18/PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = Variables.bitPlayer;
		fdef.filter.maskBits = Variables.bitPlatform;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot"); 
		
		//CREATE PLAYER
		
		player = new Player(body);
		
	}
	
}
