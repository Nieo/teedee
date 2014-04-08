package com.me.teedee.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.me.teedee.Map;
import com.me.teedee.Path;
import com.me.teedee.Player;
import com.me.teedee.Position;
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
		}
		
//		if(i%60 == 0) {
//			if(k < enemyList.size()) {
//				k++;
//			}
//		}

		renderer.getSpriteBatch().end();		

		i++;
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();		
	}

	@Override
	public void show() {

		/*TODO i framtiden kanske vi vill få in olika maps här och
		 * därför byta detta till något dynamiskt.
		 */
		map = new TmxMapLoader().load("map/mapTest.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();

		// Centers the camera to the center of the map
		TiledMapTileLayer tmp = (TiledMapTileLayer) map.getLayers().get(0);
		Vector3 center = new Vector3(tmp.getWidth() * tmp.getTileWidth() / 2, tmp.getHeight() * tmp.getTileHeight() / 2, 0);

		// FIXME Dessa två rader kanske kan flyttas till resize()
		// ev. kan camera.update() tas bort här om inte annat
		camera.position.set(center);
		camera.update();
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
	}

}
