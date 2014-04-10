package com.me.teedee.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.me.teedee.BasicTower;
import com.me.teedee.Bullet;
import com.me.teedee.Map;
import com.me.teedee.Path;
import com.me.teedee.Player;
import com.me.teedee.Position;
import com.me.teedee.Tower;
import com.me.teedee.Wave;


/**
 * A screen that represents a the map with its
 * enemies and towers on the screen.
 * @author Daniel
 */
public class MapScreen implements Screen {

	private Map m;
	private Stage hud;
	private Table table;
	private Group mapGroup;
	private Group hudGroup;

	//The bullet should NOT be created here! Only for test purposes 
	//Bullet bullet = new Bullet(600,350,100,0,2f,new Texture("img/RedBullet.png"));

	int k = 0;

	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	int i = 0;
	
	private List<Bullet> bulletList = new ArrayList();


	public MapScreen() {
		//Specifying the path positions
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,490));
		pathPositions.add(new Position(740,490));
		pathPositions.add(new Position(740,300));
		pathPositions.add(new Position(160,300));
		pathPositions.add(new Position(160,90));
		pathPositions.add(new Position(880,90));

		//Creating the path
		Path path = new Path(pathPositions);

		//Creating the wave and adding enemies to it
		int[] enemies = {5};
		Wave wave0 = new Wave(path, enemies);

		//Adding the wave to the list of waves
		ArrayList<Wave> waveList = new ArrayList<Wave>();
		waveList.add(wave0);

		//Creating a player
		Player player = new Player();

		//Creating the map
		m = new Map(waveList, path, player);
		
		//Building a BasicTower
		m.buildTower(new BasicTower(new Position(180,575),wave0.getEnemies()), new Position(180f,575f));

		for(int i = 0; i < m.getEnemies().size(); i++) {
			enemyList.add(new EnemyView(new Sprite(new Texture("img/firstEnemy.png")), m.getEnemies().get(i)));
		}
		

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		m.update();
		for (Tower tower : m.getTowers()){
			if(tower.isShooting()){ //TODO Fix line under this, could be shorter
				System.out.println("Target position: " + tower.getTargetPosition().getX() + ";" + tower.getTargetPosition().getY());
				bulletList.add(new Bullet(tower.getPosition(),new Position(tower.getTargetPosition().getX(),tower.getTargetPosition().getY()),7f,new Texture("img/RedBullet.png")));
			}
		}

		hud.act(delta);
		hud.draw();
		Table.drawDebug(hud);
		hud.getSpriteBatch().begin();

		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(hud.getSpriteBatch());
		}
		
		for(Bullet bullet : bulletList){
			bullet.draw(hud.getSpriteBatch());
		}

		//		if(i%60 == 0) {
		//			if(k < enemyList.size()) {
		//				k++;
		//			}
		//		}
		
		hud.getSpriteBatch().end();

		i++;
	}

	@Override
	public void resize(int width, int height) {
		hud.getViewport().update(width, height, true);

	}

	@Override
	public void show() {		
		hud = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // OR
		hud.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(hud);

		hudGroup = new Group();
		mapGroup = new Group();

		Image mapImg = new Image(new Texture("map/awesome_map.png"));
		mapGroup.addActor(mapImg);
		
		Image buildImg = new Image(new Texture("img/buildTest.png"));
		//img.setFillParent(true);
		
		Table guiTable = new Table();
		Table towerInfoTable = new Table();
		
		towerInfoTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png"))).top();
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png")));
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png")));
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png"))).row();
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png")));
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png")));
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png")));
		towerInfoTable.add(new Image(new Texture("img/twitterEnemy.png")));
		
		Table buildTable = new Table();
		//buildTable.add(buildImg).height(Gdx.graphics.getHeight());
		buildTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		buildTable.add(new Image(new Texture("img/twitterEnemy.png"))).top();
		buildTable.add(new Image(new Texture("img/twitterEnemy.png")));
		buildTable.add(new Image(new Texture("img/twitterEnemy.png")));
		buildTable.add(new Image(new Texture("img/twitterEnemy.png"))).row();
		buildTable.add(new Image(new Texture("img/twitterEnemy.png")));
		buildTable.add(new Image(new Texture("img/twitterEnemy.png")));
		buildTable.add(new Image(new Texture("img/twitterEnemy.png")));
		buildTable.add(new Image(new Texture("img/twitterEnemy.png")));
		buildTable.debug();
		//table.setHeight(Gdx.graphics.getHeight());
		
		guiTable.add(buildTable).row();
		guiTable.add(towerInfoTable);
		
		table = new Table();
		table.debug();
		table.add(mapImg);
		table.add(guiTable);
		table.setFillParent(true);
		table.bottom().left();
		//hudGroup.addActor(table);


		//hud.addActor(mapGroup);
		hud.addActor(table);


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
		hud.dispose();
	}

}
