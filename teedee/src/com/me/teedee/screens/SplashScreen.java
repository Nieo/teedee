package com.me.teedee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.teedee.TeeDeeGame;

/**
 * SplashScreen class
 * Intended to be the first thing that shows when you start the game.
 * @author Daniel
 */
public class SplashScreen implements Screen {
	
	private Sprite splashSprite;
	private SpriteBatch batch;
	private Texture splashTexture;
	private TeeDeeGame game;

	public SplashScreen() {
		super();
	}
	
	public SplashScreen(TeeDeeGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
		
		if(Gdx.input.justTouched()) { //after the splashScreen have faded in and out show mainScreens
			System.out.println("INPUT!!!");	//debug
			((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
		}
	}

	@Override
	public void resize(int width, int height) {
		
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
		dispose();	// to save up memory
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		splashTexture.dispose();
	}
	
}
