package com.me.TeeDee;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "teedee";
		//cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.foregroundFPS = 0;
		cfg.backgroundFPS = 0;
		cfg.vSyncEnabled = false;
		
		new LwjglApplication(new com.me.teedee.screens.TeeDeeGame(), cfg);
	}
}
