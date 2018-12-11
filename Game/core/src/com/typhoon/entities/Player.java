package com.typhoon.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Player extends Sprites {
	
	int totalHP;
	int currentHP;
	int damage;
	
	String currentPU;
	
	public Player(Body body) {
		
		super(body);

	}

}
