package com.me.teedee.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

//import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * MainMenu Class
 * This will show after the splashscreen and will
 * host buttons to start and exit a game.
 * @author Daniel
 *
 */

public class MainMenuScreen implements Screen {


	// remove a lot of this stuff into a .json file so
	// we dont have to write this all the time in every class or 
	// add textures and fonts etc. to every button

	private Stage stage;
	private Table table;
	private BitmapFont font;
	//private Label heading;
	private TextureAtlas atlas;
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

		Table.drawDebug(stage);	// TODO debug
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}


	// TODO Snygga upp show() genom att använda .json
	@Override
	public void show() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);

		table = new Table(skin);
		table.setFillParent(true);

		font = new BitmapFont(Gdx.files.internal("font/font.fnt"), false); 
		// TODO put in .json file
		// Then we dont have to do this...
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		//textButtonStyle.over = skin.getDrawable("button.over");      // TODO hover etc...
		textButtonStyle.font = font;

		playButton = new TextButton("New Game", textButtonStyle);
		playButton.pad(20);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MapScreen()); // TODO snygg övergång
			}
		});

		exitButton = new TextButton("Exit", textButtonStyle);
		exitButton.pad(20);

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		table.add(playButton).width(200).spaceBottom(20).row();
		table.add(exitButton).width(200);
		table.debug(); // TODO debug

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
