package com.me.teedee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
/**
 * A screen for selecting difficulty.
 * @author Fridgeridge
 *
 */
public class DiffSelScreen implements Screen {
	
	private Stage stage;
	private SpriteBatch batch;
	private Table table;
	
	private Skin skin;
	private Texture background;
	private Sprite bSprite;
	
	private TextButton easyButton;
	private TextButton normalButton;
	private TextButton hardButton;
	private TextButton startGame;
	
	private ButtonGroup bg;
	
	
	
	private int diff=2;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		bSprite.draw(batch);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		
		skin  = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		table = new Table(skin);
		table.setFillParent(true);
		
		
		
		DiffListener dl = new DiffListener();
		
		easyButton = new TextButton("Easy", skin);
		easyButton.setName("Easy");
		easyButton.addListener(dl);
		easyButton.setColor(Color.GRAY);
		
		normalButton = new TextButton("Normal", skin);
		normalButton.setName("Normal");
		normalButton.addListener(dl);
		normalButton.setColor(Color.RED);
		
		hardButton = new TextButton("Hard", skin);
		hardButton.setName("Hard");
		hardButton.addListener(dl);
		hardButton.setColor(Color.GRAY);
		
		startGame = new TextButton("Start", skin);
		startGame.setName("Start");
		startGame.addListener(dl);
		
		normalButton.setChecked(true);
		
		
		bg = new ButtonGroup(easyButton, normalButton, hardButton);
		bg.setMaxCheckCount(1);
		bg.setMinCheckCount(0);
		bg.setUncheckLast(true);
		
		
		table.add(easyButton).width(200).spaceBottom(20).row();
		table.add(normalButton).width(200).spaceBottom(20).row();
		table.add(hardButton).width(200).spaceBottom(20).row();
		
		table.add(startGame).width(200).height(50);
		
		
		
		stage.addActor(table);
		
		batch = new SpriteBatch();	
		
		background = new Texture("data/MainMenu.png");
		bSprite = new Sprite(background);
		bSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}

	@Override
	public void hide() {
		dispose();	
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
		stage.dispose();
		batch.dispose();
		background.dispose();
	}
	
	private class DiffListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
		System.out.println(event.getListenerActor().getName());
		String s = event.getListenerActor().getName();
		
		if(s.equals("Easy")){
			DiffSelScreen.this.diff=1;	
		}else if(s.equals("Normal")){
			DiffSelScreen.this.diff=2;
		}else if(s.equals("Hard")){
			DiffSelScreen.this.diff=3;
		}
		
		for(Button tb: bg.getButtons()){
			if(tb.isChecked()){
				tb.setDisabled(true);
				tb.setColor(Color.RED);
			}else{
				tb.setDisabled(false);
				tb.setColor(Color.GRAY);
			}
		}
		
		
		if(event.getListenerActor().getName().equals("Start")){
			System.out.println("Difficulty is "+diff);
		((Game) Gdx.app.getApplicationListener()).setScreen(new MapScreen(diff,1));
		}
		}
	}
	
	
	public int getDifficulty(){
		return diff;
	}

}
