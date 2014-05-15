package com.me.teedee.screens;

import com.badlogic.gdx.Game;

public class TeeDeeGame extends Game {

	@Override
	public void create() {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	@Override
	public void pause() {
		super.pause();

	}

	@Override
	public void resume() {
		super.resume();
	}
}
