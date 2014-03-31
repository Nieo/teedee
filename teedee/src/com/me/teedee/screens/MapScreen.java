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
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(0));
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(1));
		
		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(renderer.getSpriteBatch());
		}
		
		renderer.getSpriteBatch().end();
		
		if(i%60 == 0) {
			enemyList.add(new EnemyView(new Sprite(new Texture("img/twitterEnemy.png")), (TiledMapTileLayer) map.getLayers().get(0)));
			System.out.println(i + "");
		}
		
		i++;
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		//camera.position.set(width / 2f, height / 2f, 0);
		camera.update();		
	}

	@Override
	public void show() {
		map = new TmxMapLoader().load("map/mapTest.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		
		// Centers the camera to the center of the map
		TiledMapTileLayer tmp = (TiledMapTileLayer) map.getLayers().get(0);
		Vector3 center = new Vector3(tmp.getWidth() * tmp.getTileWidth() / 2, tmp.getHeight() * tmp.getTileHeight() / 2, 0);
		camera.position.set(center);
		
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
