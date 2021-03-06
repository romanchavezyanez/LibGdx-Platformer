package com.sprite.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Adventure extends Game {
	
	ScreenGame screen;
	
	@Override
	public void create () {
		screen = new ScreenGame();
		setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		
		screen.dispose();
	}

	@Override
	public void resize(int width, int height) {
	screen.resize(width, height);
	}
}
