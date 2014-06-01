package com.me.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
/**
 *A Screen class which shows  a credit screen with the names od the creators of this game  
 */
public class CreditsScreen implements Screen {

	private Stage stage;
	private SpriteBatch batch;
	private Table table;

	private Skin skin;
	private Sprite bSprite;

	private Label header;
	private Label body;

	private TextButton backButton;

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

		header = new Label("Creators of teedee", skin);
		body = new Label("Nieo, FridgeRidge,Dannemannet,Jacob \n"
				+ "Magni memor Ludovico XII", skin);

		backButton = new TextButton("Back", skin);
		backButton.pad(20);
		backButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
			}
		});

		table = new Table(skin);
		table.setFillParent(true);

		table.add(header).spaceBottom(20).row();
		table.add(body).spaceBottom(20).row();
		table.add(backButton).row();

		stage.addActor(table);

		batch = new SpriteBatch();	

		bSprite = new Sprite(Assets.manager.get("data/MainMenu.png", Texture.class));
		bSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void hide() { dispose();	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
	}
}
