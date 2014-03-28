package com.me.teedee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * SplashScreen class
 * Intended to be the first thing that shows when you start the game
 * @author Dannemannet
 */
public class SplashScreen implements Screen {
	
	private Sprite splashSprite;
	private SpriteBatch batch;
	private Texture splashTexture;


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		splashSprite.draw(batch);
		batch.end();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		splashTexture = new Texture("data/TeeDee.png");
		splashSprite = new Sprite(splashTexture);
		splashSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
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
	public void dispose() {
		batch.dispose();
		splashTexture.dispose();
	}
	
}
