package com.sprite.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player {
	
	//PLAYER CHARACTERISITCS.
	String name;
	int health;
	
	float xCord;			//WORLD UNITS PER SECOND
	float yCord;
	private Rectangle position;
	float projectileWidth,  projectileHeight;
	float currentJumpLevel = 0;
	public float getCurrentJumpLevel() {
		return currentJumpLevel;
	}







	public void setCurrentJumpLevel(float currentJumpLevel) {
		this.currentJumpLevel = currentJumpLevel;
	}







	TextureRegion playerReg, projectileReg;
	float timeBtwShots;
	float timeSinceLastShot;

float speed;
	public Player(
			String name, 
			int health, 
			float xCord, 
			float yCord, 
			TextureRegion player,
			float projectileWidth,
			float projectileHeight,
			float speed ,
			float timeBtwShots
			, TextureRegion laser) {
		super();
		this.name = name;
		this.health = health;
		this.xCord = xCord;
		this.yCord = yCord;
		this.playerReg = player;
		this.projectileReg = laser;
		this.projectileHeight = projectileHeight;
		this.projectileWidth = projectileWidth;
		this.speed = speed;
		this.timeBtwShots = timeBtwShots;
		
	
	}
	
	
	
	



	public Player(String name, int health, float xCord, float yCord, TextureRegion playerReg) {
		super();
		this.name = name;
		this.health = health;
		this.xCord = xCord;
		this.yCord = yCord;
		this.playerReg = playerReg;
		position = new Rectangle(xCord, yCord, 5, 10);
	}







	public TextureRegion getPlayerReg() {
		return playerReg;
	}







	public void setPlayerReg(TextureRegion playerReg) {
		this.playerReg = playerReg;
	}







	public void update(float deltaTime) {
		
		timeBtwShots += deltaTime;
		
		
	}
	
	public boolean canFire() {
		 
		boolean canFire = ( timeSinceLastShot - timeBtwShots ) >=0;
		
		return canFire;
	}
	public void draw(Batch batch) {
		batch.draw(playerReg, xCord, yCord);
		
		
		
		
	}







	public Rectangle getPosition() {
		return position;
	}
	
	
	

	

}
