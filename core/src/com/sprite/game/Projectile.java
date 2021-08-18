package com.sprite.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Projectile {
	
	
	 float movementSpeed;
	 
	 String direction;
	 
	 public Projectile(float movementSpeed, float cordX, float cordY, float width, float height,
			TextureRegion textureReg,String direction) {
		super();
		this.movementSpeed = movementSpeed;
		this.direction = direction;
		this.cordX = cordX;
		this.cordY = cordY;
		this.width = width;
		this.height = height;
		this.textureReg = textureReg;
	}




	float cordX;
	 float cordY;
	
	float  width,height;
	
	
	
	TextureRegion textureReg;



	public Projectile(float movementSpeed, float cordX, float cordY, float width, float height,
			TextureRegion textureReg) {
		super();
		this.movementSpeed = movementSpeed;
		this.cordX = cordX;
		this.cordY = cordY;
		this.width = width;
		this.height = height;
		this.textureReg = textureReg;
		
		
		
	}

	
	
	
	public void draw(Batch batch) {
		
		
		
		update();
		
		
		batch.draw(textureReg, cordX- width/2,cordY, width, height);
		
		
		
		
		
	}
	
	public void update() {
	LinkedList<Projectile> laserHolder =	ScreenGame.getLaserHolder();
		
		for (int i = 0; i < laserHolder.size(); i++) {
			
			
			if (laserHolder.get(i).direction.equals("right")) {
				
				laserHolder.get(i).cordX += laserHolder.get(i).movementSpeed;
				
			}
			
			else {
				
				laserHolder.get(i).cordX -= laserHolder.get(i).movementSpeed;
				
			}
			
			
		}
		
		
for (int i = 0; i < laserHolder.size(); i++) {
			
			
			if (laserHolder.get(i).cordX> 500) {
				
				laserHolder.remove(i);
				
			}
			
			
			else if (laserHolder.get(i).cordX<0) {
				
				laserHolder.remove(i);
				
				
			}
			
		}
		
	}
	
}
