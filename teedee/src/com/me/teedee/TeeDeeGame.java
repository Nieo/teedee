package com.me.teedee;

import com.badlogic.gdx.Game;
import com.me.teedee.screens.MainMenuScreen;
import com.me.teedee.screens.SplashScreen;

public class TeeDeeGame extends Game {
	
	public MainMenuScreen mainScreen;
	
	@Override
	public void create() {
		mainScreen = new MainMenuScreen(this);
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
