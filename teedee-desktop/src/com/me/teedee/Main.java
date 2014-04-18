package com.me.TeeDee;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.teedee.TeeDeeGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "teedee";
//		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
		
		new LwjglApplication(new TeeDeeGame(), cfg);
	}
}
