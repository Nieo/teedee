package com.me.teedee;

import com.badlogic.gdx.Game;
import com.me.teedee.screens.SplashScreen;

public class TeeDeeGame extends Game {
	
	@Override
	public void create() {		
		setScreen(new SplashScreen());
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
