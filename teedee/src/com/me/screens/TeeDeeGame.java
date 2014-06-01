package com.me.screens;

import com.badlogic.gdx.Game;
/**
 * The class which is responsible for switching between screens. 
 */
public class TeeDeeGame extends Game {

	@Override
	public void create() {
		Assets.load();
		while(!Assets.manager.update()) {	//TODO debug
			System.out.println(Assets.manager.getProgress()*100 + "%");
		}
		setScreen(new SplashScreen(this));
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
	public void resume() {
		super.resume();
	}

	@Override
	public void pause() {
		super.pause();

	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.dispose();
	}
}
