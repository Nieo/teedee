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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.me.teedee.PathFactory;
/**
 * A screen for selecting difficulty.
 * @author Fridgeridge
 *
 */
public class DiffSelScreen implements Screen {

	private Stage stage;
	private SpriteBatch batch;
	private Table table;
	private Table mapTable;

	private Skin skin;
	private Texture background;
	private Sprite bSprite;

	private TextButton easyButton;
	private TextButton normalButton;
	private TextButton hardButton;
	private TextButton startGame;
	private TextButton pathButton;

	private ButtonGroup bg;

	private Image spaceMap;
	private Image explosionMap;
	private Image militaryMap;
	private Image parkMap;

	private int pathLimit = 2;
	private int diff=2;
	private int currentPathChoice=1;

	private String mapPath = "map/map.png";

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
		table.invalidateHierarchy();
	}

	@Override
	public void show() {
		pathLimit = PathFactory.nbrOfPaths;

		skin  = new Skin(Gdx.files.internal("skin/uiskin.json"));

		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		table = new Table(skin);
		table.setFillParent(true);

		mapTable = new Table();
		table.setFillParent(true);

		DiffListener dl = new DiffListener();

		spaceMap = new Image(new Texture("map/spaceMapThumbnail.png"));
		spaceMap.setName("SpaceMap");
		spaceMap.addListener(dl);

		explosionMap = new Image(new Texture("map/explosionMapSmall.png"));
		explosionMap.setName("Explosion");
		explosionMap.addListener(dl);

		militaryMap = new Image(new Texture("map/miliTmp.png"));
		militaryMap.setName("Mili");
		militaryMap.addListener(dl);

		parkMap = new Image(new Texture("map/park.png"));
		parkMap.setName("Park");
		parkMap.addListener(dl);

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

		pathButton = new TextButton("Path 1", skin);
		pathButton.setName("Path");
		pathButton.addListener(dl);
		pathButton.setColor(Color.LIGHT_GRAY);

		normalButton.setChecked(true);

		bg = new ButtonGroup(easyButton, normalButton, hardButton);
		bg.setMaxCheckCount(1);
		bg.setMinCheckCount(0);
		bg.setUncheckLast(true);

		mapTable.add(spaceMap).padRight(20).padTop(40);
		mapTable.add(explosionMap).padTop(40).row();
		mapTable.add(militaryMap).padTop(20).padRight(20).padBottom(20);
		mapTable.add(parkMap).padTop(20).padBottom(20);

		spaceMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/mapThumbnailS.png"))));

		table.add(mapTable).padBottom(20).padTop(20).row();
		table.add(easyButton).width(200).spaceBottom(20).row();
		table.add(normalButton).width(200).spaceBottom(20).row();
		table.add(hardButton).width(200).spaceBottom(40).row();
		table.add(pathButton).width(200).spaceBottom(40).row();
		table.add(startGame).width(200).height(50).padBottom(20);

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
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		background.dispose();
	}

	private class DiffListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			String s = event.getListenerActor().getName();

			if(s.equals("Easy")){
				DiffSelScreen.this.diff=1;	
			}else if(s.equals("Normal")){
				DiffSelScreen.this.diff=2;
			}else if(s.equals("Hard")){
				DiffSelScreen.this.diff=3;
			}else if(s.equals("Path")){
				if(currentPathChoice<pathLimit){
					currentPathChoice += 1;
				}else{
					currentPathChoice = 1;
				}
				DiffSelScreen.this.pathButton.getLabel().setText("Path "+currentPathChoice);
			} else if(event.getListenerActor() instanceof Image) { 
				resetImages();
				if(s.equals("SpaceMap")) {
					mapPath = "map/map.png";
					spaceMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/mapThumbnailS.png"))));
				} else if(s.equals("Explosion")) {
					mapPath = "map/explosionMap.png";
					explosionMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/explosionMapSmallS.png"))));
				} else if(s.equals("Mili")) {
					mapPath = "map/map.png";
					militaryMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/miliTmpS.png"))));
				} else if(s.equals("Park")) {
					mapPath = "map/map.png";
					parkMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/parkS.png"))));
				}
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
				((Game) Gdx.app.getApplicationListener()).setScreen(new MapScreen(diff,currentPathChoice, mapPath));
			}
		}
	}

	private void resetImages() {
		spaceMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/spaceMapThumbnail.png"))));
		explosionMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/explosionMapSmall.png"))));
		militaryMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/miliTmp.png"))));
		parkMap.setDrawable(new SpriteDrawable(new Sprite(new Texture("map/park.png"))));
	}

	public int getDifficulty(){
		return diff;
	}
}
