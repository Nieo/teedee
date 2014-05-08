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

/**
 * MainMenu Class
 * This will show after the splashscreen and will
 * host buttons to start and exit a game.
 * @author Daniel
 */

public class MainMenuScreen implements Screen {

	private Stage stage;
	private Table table;
	private Skin skin;
	private TextButton playButton;
	private TextButton exitButton;
	private Sprite mainSprite;
	private SpriteBatch batch;
	private Texture mainTexture;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		mainSprite.draw(batch);
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
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		table = new Table(skin);
		table.setFillParent(true);

		playButton = new TextButton("New Game", skin);
		playButton.pad(20);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new DiffSelScreen()); // TODO snygg övergång
			}
		});

		exitButton = new TextButton("Exit", skin);
		exitButton.pad(20);

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		table.add(playButton).width(200).spaceBottom(20).row();
		table.add(exitButton).width(200);

		stage.addActor(table);

		batch = new SpriteBatch();
		mainTexture = new Texture("data/MainMenu.png");
		mainSprite = new Sprite(mainTexture);
		mainSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		stage.dispose();
		batch.dispose();
		mainTexture.dispose();

		// TODO maybe more things should be disposed here
	}

}
