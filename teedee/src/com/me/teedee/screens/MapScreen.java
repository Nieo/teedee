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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
import com.me.teedee.Tower;
import com.me.teedee.Wave;
import com.me.teedee.WaveCreator;


/**
 * A screen that represents a the map with its
 * enemies and towers on the screen.
 * @author Daniel
 */
public class MapScreen implements Screen {

	private Map m;
	private Stage hud;
	private Table table;

	private Label towerName;
	private Label towerKills;

	private Sprite radius;

	//The bullet should NOT be created here! Only for test purposes 
	//Bullet bullet = new Bullet(600,350,100,0,2f,new Texture("img/RedBullet.png"));

	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	int i = 0;

	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private List<TowerView> towerList = new ArrayList<TowerView>();
	private int towerIndex = 0;			// TODO change this shit

	FPSLogger fps = new FPSLogger();		// TODO debug

	Image tmp;

	private TowerView chosedTower;
	private Label moneyLabel;
	private Label hpLabel;


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

		//Adding the wave to the list of waves
		ArrayList<Wave> waveList = new ArrayList<Wave>();
		waveList = WaveCreator.creatEasyWave(path);

		//Creating a player
		Player player = new Player();

		//Creating the map
		m = new Map(waveList, path, player);

		//Building a BasicTower
		//m.buildTower(new BasicTower(new Position(180,575),wave0.getEnemies()), new Position(180f,575f));

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
		for (Tower tower : m.getTowers()){
			if(tower.isShooting()){ //TODO Fix line under this, could be shorter
				bulletList.add(new Bullet(tower.getPosition().getX() + 45,tower.getPosition().getY() + 40,tower.getTargetPosition().getX(),tower.getTargetPosition().getY(),7f,new Texture("img/RedBullet.png")));
				bulletList.add(new Bullet(tower.getPosition().getX() + 45,tower.getPosition().getY() + 40,tower.getTargetPosition().getX(),tower.getTargetPosition().getY(),14f,new Texture("img/RedBullet.png")));
			}
		}

		hpLabel.setText("HP: " + m.getPlayer().getLives().getLivesHealth());
		moneyLabel.setText("$ " + m.getPlayer().getMoneyInt());

		if(chosedTower != null) {
			towerKills.setText("Enemies killed: " + chosedTower.getKills());
		}

		hud.act(delta);
		hud.draw();
		Table.drawDebug(hud);
		hud.getSpriteBatch().begin();

		if(radius != null) {
			radius.draw(hud.getSpriteBatch());
		}

		if(tmp != null) {
			tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
			if(radius == null) {
				radius = new Sprite(new Texture("img/radius200.png"));
			}
			radius.setAlpha(1);
			radius.setPosition(tmp.getX()-200+45, tmp.getY()-200+40);
		}

		Sprite[] tiledPath = new Sprite[m.getPath().getPositions().size()];//TODO Should not be instanced in the render method
		for(int i=0; i<m.getPath().getPositions().size()-1; i++){//As of now renders the path somewhat, should probably not be an sprite. If possible use another more suitable class.  
			float x1,x2,y1,y2,dx,dy;//TODO Leaves a square to be rendered
			x1=m.getPath().getPositions().get(i).getX();
			x2=m.getPath().getPositions().get(i+1).getX();
			y1=m.getPath().getPositions().get(i).getY();
			y2=m.getPath().getPositions().get(i+1).getY();

			tiledPath[i]=new Sprite(new Texture("img/pathTile.png"));
			tiledPath[i].setRegion(x1,y1,x2,y2);

			dx=(Math.abs(x2-x1)>0?(x2-x1):50);
			dy=(Math.abs(y2-y1)>0?(y2-y1):50);
			tiledPath[i].setBounds(x1, y1, dx, dy);

			tiledPath[i].draw(hud.getSpriteBatch());

		}

		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(hud.getSpriteBatch());
			if(!enemyList.get(i).isAlive()){
				enemyList.get(i).setAlpha(0);
			}
		}

		for(Bullet bullet : bulletList){
			bullet.draw(hud.getSpriteBatch());
			if(bullet.hasHitTarget()){
				//bulletList.remove(bullet);
			}
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

	public TowerView clickedOnTower(float x, float y) {
		for(TowerView tv : towerList) {
			if(tv.contains(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY())) {
				return tv;
			}
		}
		return null;	
	}

	@Override
	public void show() {
		Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		hud = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // OR
		hud.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(hud);

		Image mapImg = new Image(new Texture("map/awesome_map.png"));
		mapImg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tmp != null) {
					tmp.setVisible(false);		// TODO the image still exists under the tower, or does it?
					tmp = null;
					int tmpX = Gdx.input.getX()-45;
					int tmpY = Gdx.graphics.getHeight()-Gdx.input.getY()-40;
					m.buildTower(new BasicTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY));
					towerList.add(new TowerView(new Sprite(new Texture("img/firstDragon.png")), m.getTowers().get(towerIndex)));
					towerIndex++;
				} else {
					chosedTower = clickedOnTower(Gdx.input.getX(), Gdx.input.getY());
					if(chosedTower != null) {
						radius = new Sprite(new Texture("img/radius200.png"));
						radius.setX(chosedTower.getX()-200+45);
						radius.setY(chosedTower.getY()-200+40);
						radius.setAlpha(1);
						towerName.setText(chosedTower.getName());
						towerKills.setText("Enemies killed: " + chosedTower.getKills());
					} else {
						if(radius != null) {
							radius.setAlpha(0);
						}
					}
				}
			}
		});

		Table guiTable = new Table();
		Table towerInfoTable = new Table();

		Image tw = new Image(new Texture("img/firstDragon.png"));
		tw.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tmp != null) {
					tmp.setVisible(false);		// TODO the image still exists under the tower, or does it?
					tmp = null;
				}
				
				tmp = new Image(new Texture("img/firstDragon.png"));
				tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
				tmp.setTouchable(null);
				hud.addActor(tmp);
			}
		});

		TextButton upgradeBtn = new TextButton("Upgrade", uiSkin);
		TextButton sellBtn = new TextButton("Sell", uiSkin);
		TextButton nextWaveBtn = new TextButton("Next Wave", uiSkin);
		upgradeBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(chosedTower != null) {
					//chosedTower.upgrade();		//TODO this doesn't work
				}
			}
		});

		sellBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(chosedTower != null) {
					chosedTower.sell();
				}
				//TODO remove tower from towerList
			}
		});

		nextWaveBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO if wave is over
				m.nextWave();
			}
		});

		towerInfoTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		towerInfoTable.add(towerName = new Label("Tower Name", uiSkin)).row();
		towerInfoTable.add(towerKills = new Label("Tower Name", uiSkin)).row();
		towerInfoTable.add(upgradeBtn).width(100).height(70).padBottom(20).padTop(20).row();
		towerInfoTable.add(sellBtn).width(100).height(70);

		Table buildTable = new Table();
		buildTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		buildTable.add(hpLabel = new Label("HP: " + m.getPlayer().getLives().getLivesHealth(), uiSkin)).padTop(10).row();
		buildTable.add(moneyLabel = new Label("$ " + m.getPlayer().getMoneyInt(), uiSkin)).padBottom(30).row();
		buildTable.add(tw).top().padLeft(20);
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
		buildTable.add(new Image(new Texture("img/firstDragon.png"))).padRight(20).row();
		buildTable.add(new Image(new Texture("img/firstDragon.png"))).padLeft(20);
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
		buildTable.add(new Image(new Texture("img/firstDragon.png"))).padRight(20);
		buildTable.debug();
		buildTable.top();

		guiTable.add(buildTable).row();
		guiTable.add(towerInfoTable).width(315).row();
		guiTable.add(nextWaveBtn).width(200).height(100).padTop(20).padBottom(20);

		table = new Table();
		table.debug();
		table.add(mapImg);
		table.add(guiTable);
		table.setFillParent(true);
		table.bottom().left();

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
