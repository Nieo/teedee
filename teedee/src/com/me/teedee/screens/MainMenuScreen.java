package com.me.teedee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.me.teedee.TeeDeeGame;

/**
 * MainMenu Class
 * This will show after the splashscreen and will
 * host buttons to start and exit a game.
 * @author Daniel
 *
 */

public class MainMenuScreen implements Screen {

	private Stage stage;
	private Table table;
	private BitmapFont font;
	private Label heading;
	private TextureAtlas atlas;
	private Skin skin;
	private TextButton playButton;
	private TextButton exitButton;
	private Sprite mainSprite;
	private SpriteBatch batch;
	private Texture mainTexture;

	private TeeDeeGame game;

	public MainMenuScreen(TeeDeeGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		mainSprite.draw(batch);
		batch.end();
		
		Table.drawDebug(stage);		//debug
		stage.act(delta);
		stage.draw();
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("");	// TODO put in picture (button pack) #6
		skin = new Skin(atlas);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		font = new BitmapFont(Gdx.files.internal(""), false); // TODO put in bitmapfont

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("");		// up picture
		textButtonStyle.down = skin.getDrawable("");	// down picture
		// hover etc...

		//		textButtonStyle.pressedOffsetX = 1;				// Text on button changes position when clicked
		//		textButtonStyle.pressedOffsetY = -1;

		//textButtonStyle.font = font;
		playButton = new TextButton("New Game", textButtonStyle);
		playButton.pad(20);

		exitButton = new TextButton("Exit", textButtonStyle);
		exitButton.pad(20);

		table.add(playButton);
		table.add(exitButton);
		table.debug(); // TODO sdebug REMOVE LATER

		stage.addActor(table);

		batch = new SpriteBatch();
		mainTexture = new Texture("data/teedee_games.png");
		mainSprite = new Sprite(mainTexture);
		mainSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
