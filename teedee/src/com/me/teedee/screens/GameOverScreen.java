package com.me.teedee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen implements Screen{
	private Stage stage;
	private SpriteBatch batch;
	private Table table;

	private Skin skin;
	private Texture background;
	private Sprite backgroundSprite;

	private TextButton newGameButton;
	private TextButton exitButton;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		backgroundSprite.draw(batch);
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

		newGameButton = new TextButton("New Game", skin);
		newGameButton.pad(20);
		newGameButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new DiffSelScreen());
			}
		});

		exitButton = new TextButton("Quit", skin);
		exitButton.pad(20);
		exitButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});

		table = new Table(skin);
		table.setFillParent(true);

		table.add(newGameButton).width(200).spaceBottom(20).row();
		table.add(exitButton).width(200).row();

		stage.addActor(table);

		batch = new SpriteBatch();	
		background = new Texture("data/GAME_OVER_Screen.png");
		backgroundSprite = new Sprite(background);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		background.dispose();
	}
}
