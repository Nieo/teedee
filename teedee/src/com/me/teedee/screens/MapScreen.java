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
import com.me.teedee.Bullet;
import com.me.teedee.Map;
import com.me.teedee.Path;
import com.me.teedee.Player;
import com.me.teedee.Position;
import com.me.teedee.WaveCreator;
import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.towers.AbstractTower;
import com.me.teedee.towers.BasicTower;
import com.me.teedee.towers.IceTower;


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

	private RadiusImage radius;

	private Image chosedTowerImage;

	//The bullet should NOT be created here! Only for test purposes 
	//Bullet bullet = new Bullet(600,350,100,0,2f,new Texture("img/RedBullet.png"));

	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	int i = 0;
	private int waveIndex = 0;

	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private List<TowerView> towerList = new ArrayList<TowerView>();
	private int towerIndex = 0;			// TODO change this shit, maybe not, i dont know

	FPSLogger fps = new FPSLogger();		// TODO debug

	Image tmp;						// TODO tmp varaible, should probably create new class to handle this
	private int buildIndex = 0;		//	^this

	private TowerView chosedTower;
	private Label moneyLabel;
	private Label hpLabel;
	protected boolean buildAble;

	private Sprite[] tiledPath;


	public MapScreen() {
		//Specifying the path positions
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,490));
		pathPositions.add(new Position(740,490));
		pathPositions.add(new Position(740,300));
		pathPositions.add(new Position(160,300));
		pathPositions.add(new Position(160,90));
		pathPositions.add(new Position(850,90));
		pathPositions.add(new Position(850,720));

		//Creating the path
		Path path = new Path(pathPositions);

		//Creating a player
		Player player = new Player();

		//Creating the map
		m = new Map(WaveCreator.creatEasyWave(path), path, player);

		tiledPath = new Sprite[m.getPath().getPositions().size()];

		for(int i=0; i<m.getPath().getPositions().size()-1; i++){
			float x1,x2,y1,y2,dx,dy;//TODO Leaves a square to be rendered
			x1=m.getPath().getPositions().get(i).getX();
			x2=m.getPath().getPositions().get(i+1).getX();
			y1=m.getPath().getPositions().get(i).getY();
			y2=m.getPath().getPositions().get(i+1).getY();

			tiledPath[i]=new Sprite(new Texture("img/pathTile.png"));
			dx = x2-x1;
			dy = y2-y1;
			if(dx > 0)
				tiledPath[i].setBounds(x1+20, y1+30, dx+60, 60);			
			else if(dx < 0)
				tiledPath[i].setBounds(x1+80, y1+30, dx-60, 60);
			else if(dy > 0)
				tiledPath[i].setBounds(x1+30, y1+30, 60, dy+60);
			else if(dy < 0)
				tiledPath[i].setBounds(x1+20, y1+90, 60, dy-60);
			//else
				//tiledPath[i].setBounds(x1, y1-30, dx, dy);
		}

		for(int i = 0; i < m.getEnemies().size(); i++) {
			enemyList.add(new EnemyView(new Sprite(new Texture("img/firstEnemy.png")), m.getEnemies().get(i)));
		}

		chosedTowerImage = new Image(new Texture("img/unknown.png"));
		radius = new RadiusImage(new Texture("img/radius200.png"));
	}

	@Override
	public void render(float delta) {
		//fps.log();		// TODO debug
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		m.update();

		for (AbstractTower tower : m.getTowers()){
			if(tower.isShooting()){ //TODO Fix line under this, could be shorter
				bulletList.add(new Bullet(tower.getPosition().getX() + 45,tower.getPosition().getY() + 40,tower.getTargetPosition().getX(),tower.getTargetPosition().getY(),7f,new Texture("img/RedBullet.png")));
				bulletList.add(new Bullet(tower.getPosition().getX() + 45,tower.getPosition().getY() + 40,tower.getTargetPosition().getX(),tower.getTargetPosition().getY(),14f,new Texture("img/RedBullet.png")));
			}
		}

		if(waveIndex != m.getWaveIndex()) {
			for(int i = 0; i < m.getEnemies().size(); i++) {
				enemyList.add(new EnemyView(new Sprite(new Texture("img/firstEnemy.png")), m.getEnemies().get(i)));
			}
			waveIndex = m.getWaveIndex();
		}

		hpLabel.setText("HP: " + m.getPlayer().getLives().getLivesHealth());
		moneyLabel.setText("$ " + m.getPlayer().getMoneyInt());

		if(chosedTower != null) {
			towerName.setText(chosedTower.getName() + " Lv." + chosedTower.getCurrentLevel());
			towerKills.setText("Enemies killed: " + chosedTower.getKills());
			chosedTowerImage.setDrawable(new SpriteDrawable(new Sprite(chosedTower.getTexture())));
		} else {
			towerName.setText("Tower Name");
			towerKills.setText("Enemies killed");
			chosedTowerImage.setDrawable(new SpriteDrawable(new Sprite(new Texture("img/unknown.png"))));
		}

		hud.act(delta);
		hud.draw();
		Table.drawDebug(hud);
		hud.getSpriteBatch().begin();

		//TODO Fix the color changer
		if(tmp != null) {
			tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
			radius.showRadius();
			radius.setPosition(tmp.getX(), tmp.getY());
		} else if(tmp == null && chosedTower == null) {
			radius.hideRadius();
		}

		for(int i=0; i<m.getPath().getPositions().size()-1; i++){//As of now renders the path somewhat, should probably not be an sprite. If possible use another more suitable class.  
			tiledPath[i].draw(hud.getSpriteBatch());
		}

		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(hud.getSpriteBatch());
			if(!enemyList.get(i).isAlive() || enemyList.get(i).reachedEnd()){
				enemyList.get(i).setAlpha(0);
				enemyList.remove(i);
			}
		}

		//This loop does not work with for-each
		for(int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).draw(hud.getSpriteBatch());
			if(bulletList.get(i).hasHitTarget()){
				bulletList.get(i).setAlpha(0);
				bulletList.remove(i);
			}
		}

		radius.draw(hud.getSpriteBatch());

		if(tmp != null) {
			tmp.draw(hud.getSpriteBatch(), 1);
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

		Image mapImg = new Image(new Texture("map/map.png"));
		mapImg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tmp != null) {
					tmp.setVisible(false);		// TODO the image still exists under the tower, or does it?
					tmp = null;					//TODO dont thinks this is needed
					int tmpX = Gdx.input.getX()-45;
					int tmpY = Gdx.graphics.getHeight()-Gdx.input.getY()-40;
					switch(buildIndex) {		//TODO probably should do something else than this
					case 1:
						if(m.buildTower(new BasicTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY))) {
							towerList.add(new TowerView(new Sprite(new Texture("img/firstDragon.png")), m.getTowers().get(towerIndex), towerIndex));
							towerIndex++;
							buildIndex = 0;
						}
						break;
					case 2:
						if(m.buildTower(new IceTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY))) {
							towerList.add(new TowerView(new Sprite(new Texture("img/iceDragon.png")), m.getTowers().get(towerIndex), towerIndex));
							towerIndex++;
							buildIndex = 0;
						}
						break;
					case 3:
						if(m.buildTower(new MultiTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY))) {
							towerList.add(new TowerView(new Sprite(new Texture("img/firstDragon.png")), m.getTowers().get(towerIndex), towerIndex));
							towerIndex++;
							buildIndex = 0;
						}
						break;

						
					default:
						System.out.println("No such tower exists"); 	//TODO debug
						break;
					}

				} else {
					chosedTower = clickedOnTower(Gdx.input.getX(), Gdx.input.getY());
					if(chosedTower != null) {
						radius.setRadius((float) chosedTower.getTower().getRange());
						radius.setPosition(chosedTower.getX(), chosedTower.getY());
						radius.showRadius();
						towerName.setText(chosedTower.getName());
						towerKills.setText("Enemies killed: " + chosedTower.getKills());
					} else {
						radius.hideRadius();
					}
				}
			}
		});

		Table guiTable = new Table();
		Table towerInfoTable = new Table();

		Image bt = new Image(new Texture("img/firstDragon.png"));
		bt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tmp != null) {
					tmp.setVisible(false);
					tmp = null;				//TODO i dont think this is needed
				}

				tmp = new Image(new Texture("img/firstDragon.png"));
				tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
				tmp.setTouchable(null);
				radius.setRadius(200);
				buildIndex = 1;
			}
		});

		Image it = new Image(new Texture("img/iceDragon.png"));
		it.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tmp != null) {
					tmp.setVisible(false);
					tmp = null;				//TODO i dont think this is needed
				}

				tmp = new Image(new Texture("img/iceDragon.png"));
				tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
				tmp.setTouchable(null);
				radius.setRadius(150);
				buildIndex = 2;
			}
		});
		Image mt = new Image(new Texture("img/iceDragon.png"));
		mt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tmp != null) {
					tmp.setVisible(false);
					tmp = null;				//TODO i dont think this is needed
				}

				tmp = new Image(new Texture("img/iceDragon.png"));
				tmp.setPosition(Gdx.input.getX()-45, Gdx.graphics.getHeight()-Gdx.input.getY()-40);
				tmp.setTouchable(null);
				radius.setRadius(150);
				buildIndex = 3;
			}
		});
		TextButton upgradeBtn = new TextButton("Upgrade", uiSkin);
		TextButton sellBtn = new TextButton("Sell", uiSkin);
		TextButton nextWaveBtn = new TextButton("Next Wave", uiSkin);
		upgradeBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(chosedTower != null) {
					if(m.upgradeTower(chosedTower.getTower()))
						chosedTower.upgrade();
				}
			}
		});

		sellBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(chosedTower != null) {
					int tmpIndex = chosedTower.getIndex();
					m.sellTower(tmpIndex);
					chosedTower.setAlpha(0);
					towerList.remove(tmpIndex);
					for(int i = tmpIndex; i < towerList.size(); i++) {
						int oldIndex = towerList.get(tmpIndex).getIndex();
						towerList.get(i).setIndex(oldIndex - 1);
					}
					towerIndex--;
					chosedTower = null;
				}
			}
		});

		nextWaveBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				m.nextWave();
			}
		});

		towerInfoTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		towerInfoTable.add(chosedTowerImage).left().row();
		towerInfoTable.add(towerName = new Label("Tower Name", uiSkin)).left().row();
		towerInfoTable.add(towerKills = new Label("Tower Name", uiSkin)).left().row();
		towerInfoTable.add(upgradeBtn).width(100).height(70).padBottom(20).padTop(20).padRight(20);
		towerInfoTable.add(sellBtn).width(100).height(70);

		Table buildTable = new Table();
		buildTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		buildTable.add(hpLabel = new Label("HP: " + m.getPlayer().getLives().getLivesHealth(), uiSkin)).padTop(10).row();
		buildTable.add(moneyLabel = new Label("$ " + m.getPlayer().getMoneyInt(), uiSkin)).padBottom(30).row();
		buildTable.add(bt).top().padLeft(20);
		buildTable.add(it);
		buildTable.add(mt).padRight(20).row();
		buildTable.add(new Image(new Texture("img/firstDragon.png"))).padLeft(20);
		buildTable.add(new Image(new Texture("img/firstDragon.png")));
		buildTable.add(new Image(new Texture("img/firstDragon.png"))).padRight(20);
		
		//buildTable.debug();		//TODO debug
		buildTable.top();

		guiTable.add(buildTable).row();
		guiTable.add(towerInfoTable).width(315).row();
		guiTable.add(nextWaveBtn).width(200).height(100).padTop(20).padBottom(20);

		table = new Table();
		//table.debug();			//TODO debug
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
