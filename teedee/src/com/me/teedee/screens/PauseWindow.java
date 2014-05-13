package com.me.teedee.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseWindow extends Window {
	
	private TextButton resumeButton;
	private TextButton quitButton;
	
	public PauseWindow(String title, Skin skin) {
		super(title, skin);
		
		resumeButton = new TextButton("Resume Game", skin);
		quitButton = new TextButton("Quit Game", skin);
		
		this.add(resumeButton);
		this.add(quitButton);
		
		resumeButton.addListener(new ClickListener(){
		});
		
		this.setKeepWithinStage(true);
		this.setMovable(true);
		this.setVisible(false);
		
	}

}
