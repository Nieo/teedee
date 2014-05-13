package com.me.teedee.screens;

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
	float alpha = 0;
	private int counter = 0;
	private boolean fadeOut = false;
	private boolean fadeIn = true;

	public SplashScreen(TeeDeeGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		splashSprite.setAlpha(alpha);
		splashSprite.draw(batch);
		batch.end();
		
		fadeAnimation();

		if(alpha <= 0) {
			game.setScreen(new MainMenuScreen());
		}
		
		//TODO debug, REMOVE BEFORE SUBMIT!
		if(Gdx.input.justTouched()) {
			game.setScreen(new MainMenuScreen());
		}
	}
	
	private void fadeAnimation() {
		if(fadeIn ) {
			alpha += 0.01;
			if(alpha > 1) {
				alpha = 1;
				fadeIn = false;
			}
		} else if(!fadeIn && !fadeOut) {
			counter++;
			if(counter > 120) {
				fadeOut = true;
			}
		} else if(fadeOut){
			alpha -= 0.01;
			if(alpha < 0) {
				alpha = 0;
			}
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
		dispose();
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
