package com.sprite.game;

import java.util.LinkedList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;







public class ScreenGame implements Screen {

	boolean canJump = false;
	boolean fallingDown = false;
	boolean canPressUP= true;
	public Vector3 posCameraDesired;
	private static LinkedList<Projectile> laserHolder;

	public static LinkedList<Projectile> getLaserHolder() {
		return laserHolder;
	}



	String direction = "right";

	private Camera cam;
	int backDropNumber=0;


	private Viewport viewP;
	private SpriteBatch sprite;
	//	private Texture bg;

	private TextureAtlas textureAtlas;
	private TextureRegion[] bg;
	private TextureRegion[] backDrop;

	private TextureRegion playerTextureRegion, enemyTextureRegion, LaserTextureRegion;
	//	private int bgOffset;

	//private float[] bgOffsets = {0,0,0,0,0};



	private final int HEIGHT = 120;

	private final int WIDTH = 240;


	// bounds for scene change
	Rectangle bounds = new Rectangle(220,0,1,200);


	Rectangle startBounds  = new Rectangle(1,0,1,200);


	private Player mainChar;
	int workingScene;

	private Player enemy;
	//boolean changeScenes = true;
	public ScreenGame() {

		// call a method to set all the bg's
		cam= new OrthographicCamera();

		backDrop= new TextureRegion[10];
		viewP = new StretchViewport(WIDTH, HEIGHT, cam);
		textureAtlas = new TextureAtlas("img.atlas");
		bg = new TextureRegion[10];

		//mostly just textures
		bg[0] = textureAtlas.findRegion("characterr");
		bg[1] = textureAtlas.findRegion("character");
		bg[2] = textureAtlas.findRegion("2d");
		bg[3] = textureAtlas.findRegion("revised");
		bg[4] = textureAtlas.findRegion("revised2");

		TextureAtlas atlas = new TextureAtlas("laser.atlas");
		TextureAtlas third = new TextureAtlas("level3.atlas");



		mainChar = new Player("Alien",100, 25,25,bg[3] );

		//enemy = new Player("Enemy", 100, 50,50, bg[3]);



		bg[5] = atlas.findRegion("laserTRansparent");
		bg[6] = atlas.findRegion("3660");


		backDrop[0] = textureAtlas.findRegion("2d");
		backDrop[1] = atlas.findRegion("3660");
		backDrop[2]= third.findRegion("1083");
		//backDrop[3]= third.findRegion("level3");


		laserHolder= new LinkedList<>();




		sprite = new SpriteBatch();
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		// have a bg for only backgrounds so its easy.
		sprite.begin();

		detectInput(delta);

		
		workingScene = backDropNumber;

		sprite.draw(backDrop[workingScene],0,0, WIDTH,HEIGHT);

		if (bounds.overlaps(mainChar.getPosition()) ) {



			
			try {

				sprite.draw(backDrop[++backDropNumber],0,0, WIDTH,HEIGHT);
				mainChar.xCord = 2;
				mainChar.getPosition().x =2;
				//changeScenes = false;
				bounds = new Rectangle(bounds.x, bounds.y, 1 ,200);
				startBounds = new Rectangle(startBounds.x, startBounds.y, 1 ,200);

			}


			catch(Exception e) {
				backDropNumber--;
				sprite.draw(backDrop[workingScene],0,0, WIDTH,HEIGHT);

				System.out.println("Not working");
				
			}
		} 


		if (startBounds.overlaps(mainChar.getPosition())) {



			try {

				sprite.draw(backDrop[--backDropNumber],0,0, WIDTH,HEIGHT);

				System.out.println("startBounds");
				startBounds = new Rectangle(startBounds.x, startBounds.y, 1 ,200);
				bounds = new Rectangle(bounds.x, bounds.y, 1 ,200);
				mainChar.xCord = 200;
				mainChar.getPosition().x = 200;
			}


			catch(Exception e) {
				backDropNumber++;
				sprite.draw(backDrop[workingScene],0,0, WIDTH,HEIGHT);

				System.out.println("Not workingStart");
				
			}




		}


		mainChar.draw(sprite);
		


		for (int i = 0; i < laserHolder.size(); i++)
			laserHolder.get(i).draw(sprite);
		

		sprite.end();


	}

	private void detectInput(float delta) {

		// fix cords here
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& mainChar.xCord < 220) {

			mainChar.xCord +=1.4;
			mainChar.getPosition().x+=1.4;


			if (mainChar.xCord>220) {

				mainChar.xCord= 220;
				mainChar.getPosition().x=220;
			}

			mainChar.setPlayerReg(bg[4]);

			direction = "right";
			

			System.out.println(mainChar.getPosition().x);
			System.out.println(mainChar.xCord);

		}


		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& mainChar.xCord > 0) {

			mainChar.xCord -=1.4;
			mainChar.getPosition().x-=1.4;

			if (mainChar.xCord<0 && workingScene==0) {
				mainChar.getPosition().x=0;
				mainChar.xCord= 0;

			}
			

			mainChar.setPlayerReg(bg[3]);
			direction = "left";
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

			laserHolder.add(new Projectile(.5f, mainChar.xCord,mainChar.yCord+6.5f, 10, 5, bg[5], direction));




		}


		if (Gdx.input.isKeyPressed(Input.Keys.UP)&& canPressUP) {



			canJump= true;
			canPressUP = false;

		}

		if (canJump) {
			jump();

		}
		
		if (fallingDown) {
			
			falling();
		}
	}



	public void jump() {
		if (mainChar.getCurrentJumpLevel() < 20) {


			mainChar.setCurrentJumpLevel(mainChar.getCurrentJumpLevel()+1);
			mainChar.yCord+=1;

		}
		else {
			
			canJump = false;		
		
			fallingDown = true;
		mainChar.setCurrentJumpLevel(0);
		}



	}
	
	
	public void falling() {
		
		mainChar.yCord -=1;
		if (mainChar.yCord <= 25) {
			
			fallingDown = false;
			
			canPressUP = true;
			
		}
	}

	@Override
	public void resize(int width, int height) {

		viewP.update(width, height, true);
		
		sprite.setProjectionMatrix(cam.combined);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		sprite.dispose();

	}

}
