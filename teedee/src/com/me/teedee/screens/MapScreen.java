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


/**
 * A screen that represents a the map with its
 * enemies and towers on the screen.
 * @author Daniel
 */
public class MapScreen implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	int i = 0;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		
		renderer.getSpriteBatch().begin();
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(0));		//FIXME denna raden kanske kan tas bort
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(1));
		
		for(int i = 1; i < enemyList.size(); i++) {				// i = 1 f�r att en fiende redan spawnats i show()
			enemyList.get(i).draw(renderer.getSpriteBatch());
		}
		
		renderer.getSpriteBatch().end();
		
		/* TODO H�mta ut varje enemy ur en lista m�jligtvis fr�n Map klassen vi gjort
		 * och d�refter l�gga till dom i enemyList h�r.
		 * Vi beh�ver �ven f� fram en enemy type eller liknande f�r att veta vilken bild som
		 * ska l�ggas p� och vilken konstruktor som ska anropas i enemyView.
		 */
		if(i%60 == 0) {
			enemyList.add(new EnemyView(new Sprite(new Texture("img/twitterEnemy.png")), (TiledMapTileLayer) map.getLayers().get(0)));
		}
		
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
		
		/*TODO i framtiden kanske vi vill f� in olika maps h�r och
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
		
		// Spawnar den f�rsta fienden
		enemyList.add(new EnemyView(new Sprite(new Texture("img/twitterEnemy.png")), (TiledMapTileLayer) map.getLayers().get(0)));
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
