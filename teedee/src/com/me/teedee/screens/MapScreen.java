package com.me.teedee.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.me.teedee.AbstractEnemy;
import com.me.teedee.BasicTower;
import com.me.teedee.Bullet;
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

	private Map m;
	private Stage hud;
	private Table table;

	//The bullet should NOT be created here! Only for test purposes 
	Bullet bullet = new Bullet(50,320,100,0,2f,new Texture("img/RedBullet.png"));

	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	private List<TowerView> towerList = new ArrayList<TowerView>();
	private int towerIndex = 1;			// TODO change this shit
	
	FPSLogger fps = new FPSLogger();		// TODO debug
	
	Image tmp;


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
		
		//Building a BasicTower at (100;100)
		m.buildTower(new BasicTower(new Position(10,10),wave0.getEnemies()), new Position(10f,10f));

		for(int i = 0; i < m.getEnemies().size(); i++) {
			enemyList.add(new EnemyView(new Sprite(new Texture("img/firstEnemy.png")), m.getEnemies().get(i)));
		}
		
	}

	@Override
	public void render(float delta) {
		//fps.log();		// TODO debug
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		m.update();

		hud.act(delta);
		hud.draw();
		Table.drawDebug(hud);
		hud.getSpriteBatch().begin();
		if(tmp != null) {
			tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
		}

		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(hud.getSpriteBatch());


			//			ShapeRenderer shapeRenderer = new ShapeRenderer();
			//			shapeRenderer.setProjectionMatrix(camera.combined);
			//			shapeRenderer.begin(ShapeType.Filled);
			//			shapeRenderer.rectLine(0, 0,enemyList.get(i).getX(), enemyList.get(i).getY(),5);
			//			shapeRenderer.end();

			bullet.draw(hud.getSpriteBatch());

		}
		
		for(int i = 0; i< towerList.size(); i++) {
			towerList.get(i).draw(hud.getSpriteBatch());
		}
		
		hud.getSpriteBatch().end();
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

		Image mapImg = new Image(new Texture("map/awesome_map.png"));
		mapImg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("" + Gdx.input.getX());
				System.out.println("" + Gdx.input.getY());
				System.out.println();
			}
		});
		
		//Image buildImg = new Image(new Texture("img/buildTest.png"));
		//img.setFillParent(true);
		
		Table guiTable = new Table();
		Table towerInfoTable = new Table();
		
		Image tw = new Image(new Texture("img/firstDragon.png"));
		tw.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("clicked");
				tmp = new Image(new Texture("img/firstDragon.png"));
				tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
				tmp.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						tmp = null;		// TODO the image still exists under the tower
						System.out.println(Gdx.input.getX()+" "+(Gdx.graphics.getHeight()-Gdx.input.getY()-15));
						int tmpX = Gdx.input.getX()-45;
						int tmpY = Gdx.graphics.getHeight()-Gdx.input.getY()-40;
						m.buildTower(new BasicTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY));
						towerList.add(new TowerView(new Sprite(new Texture("img/firstDragon.png")), m.getTowers().get(towerIndex)));
						System.out.println(m.getTowers().size()+"");
						towerIndex++;
					}
				});
				hud.addActor(tmp);
			}
		});
		
		towerInfoTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		towerInfoTable.add(new Image(new Texture("img/firstDragon.png"))).top();
		towerInfoTable.add(new Image(new Texture("img/firstDragon.png")));
		towerInfoTable.add(new Image(new Texture("img/firstDragon.png"))).row();
		towerInfoTable.add(new Image(new Texture("img/firstDragon.png")));
		towerInfoTable.add(new Image(new Texture("img/firstDragon.png")));
		towerInfoTable.add(new Image(new Texture("img/firstDragon.png")));
		
		Table buildTable = new Table();
		//buildTable.add(buildImg).height(Gdx.graphics.getHeight());
		buildTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		buildTable.add(tw).top();
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
		buildTable.add(new Image(new Texture("img/firstDragon.png"))).row();
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
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
