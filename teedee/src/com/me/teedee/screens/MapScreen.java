package com.me.teedee.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.me.teedee.Bullet;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.me.teedee.BasicTower;
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

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Map m;
	private Stage hud;
	private Group build;
	
	
	//The bullet should NOT be created here! Only for test purposes 
	Bullet bullet = new Bullet(50,320,100,0,2f,new Texture("img/RedBullet.png"));

	int k = 0;

	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	int i = 0;


	public MapScreen() {
		//Specifying the path positions
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,50));
		pathPositions.add(new Position(100,50));
		pathPositions.add(new Position(100,200));
		pathPositions.add(new Position(300,200));
		pathPositions.add(new Position(300,10));

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
		
		//Building a BasicTower at (100;100)
		m.buildTower(new BasicTower(new Position(10,10),wave0.getEnemies()), new Position(10f,10f));

		for(int i = 0; i < m.getEnemies().size(); i++) {
			enemyList.add(new EnemyView(new Sprite(new Texture("img/twitterEnemy.png")), m.getEnemies().get(i)));
		}
		

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		m.update();

		renderer.setView(camera);

		renderer.getSpriteBatch().begin();
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(0));		//FIXME denna raden kanske kan tas bort
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(1));


		
		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(renderer.getSpriteBatch());
			
//			ShapeRenderer shapeRenderer = new ShapeRenderer();
//			shapeRenderer.setProjectionMatrix(camera.combined);
//			shapeRenderer.begin(ShapeType.Filled);
//			shapeRenderer.rectLine(0, 0,enemyList.get(i).getX(), enemyList.get(i).getY(),5);
//			shapeRenderer.end();
			
			bullet.draw(renderer.getSpriteBatch());
			
		}
		
//		if(i%60 == 0) {
//			if(k < enemyList.size()) {
//				k++;
//			}
//		}
		renderer.getSpriteBatch().end();
		
		hud.act(delta);
		hud.draw();
		
		i++;
	}

	@Override
	public void resize(int width, int height) {
		hud.getViewport().update(width, height, true);
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();		
	}

	@Override
	public void show() {
		/* TODO i framtiden kanske vi vill f� in olika maps h�r och
		 * d�rf�r byta detta till n�got dynamiskt.
		 */
		map = new TmxMapLoader().load("map/mapTest.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();

		// Centers the camera to the center of the map
		TiledMapTileLayer tmp = (TiledMapTileLayer) map.getLayers().get(0);
		Vector3 center = new Vector3(tmp.getWidth() * tmp.getTileWidth() / 2, tmp.getHeight() * tmp.getTileHeight() / 2, 0);

		// FIXME Dessa tv� rader kanske kan flyttas till resize()
		// ev. kan camera.update() tas bort h�r om inte annat
		camera.position.set(center);
		camera.update();
		
		hud = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // OR
		hud.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(hud);
		build = new Group();
		Image img = new Image(new Texture("img/buildTest.png"));
		img.setFillParent(true);
		hud.addActor(build);
		build.addActor(img);
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
		map.dispose();
		renderer.dispose();
		hud.dispose();
	}

}
