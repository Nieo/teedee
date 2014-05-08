package com.me.teedee.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.me.teedee.PathFactory;
import com.me.teedee.Player;
import com.me.teedee.Position;
import com.me.teedee.WaveCreator;
import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.towers.AbstractTower;
import com.me.teedee.towers.BasicTower;
import com.me.teedee.towers.IceTower;
import com.me.teedee.towers.MultiTower;

/**
 * A screen that represents a the map with its
 * enemies and towers on the screen.
 * @author Daniel
 */
public class MapScreen implements Screen {

	// TODO Make all possible variables more local!!!

	private Map m;
	private Stage hud;
	private Table table;

	private Label towerName;
	private Label towerKills;
	private Label moneyLabel;
	private Label hpLabel;

	private InfoImage info;
	private RadiusImage radius;
	private Image chosedTowerImage;
	private TowerView chosedTower;

	private int towerIndex = 0;		// TODO change this shit, maybe not, i dont know
	private int waveIndex = 0;
	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private List<TowerView> towerList = new ArrayList<TowerView>();

	private Sprite[] tiledPath;

	Image tmp;						// TODO tmp varaible, should probably create new class to handle this

	private int buildIndex = 0;		//	^this
	protected boolean buildAble;		//TODO remove?
	FPSLogger fps = new FPSLogger();		// TODO debug

	public MapScreen() {

		//Creating the path
		Path path = PathFactory.createPath(1);

		//Creating a player
		Player player = new Player();

		//Creating the map
		m = new Map(WaveCreator.creatHardWave(path), path, player);

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
				tiledPath[i].setBounds(x1, y1, dx, 60);			
			else if(dx < 0)
				tiledPath[i].setBounds(x1, y1, dx, 60);
			else if(dy > 0)
				tiledPath[i].setBounds(x1, y1, 60, dy);
			else if(dy < 0)
				tiledPath[i].setBounds(x1, y1+60, 60, dy-60);
			//else
			//tiledPath[i].setBounds(x1, y1-30, dx, dy);
		}

		for(int i = 0; i < m.getEnemies().size(); i++) {
			enemyList.add(new EnemyView( m.getEnemies().get(i)));
		}

		chosedTowerImage = new Image(new Texture("img/unknown.png"));
		radius = new RadiusImage(new Texture("img/radius200.png"));
		info = new InfoImage();
	}

	@Override
	public void render(float delta) {
		//fps.log();		// TODO debug
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		m.update(delta);

		updateObjects();

		hud.act(delta);
		hud.draw();
		Table.drawDebug(hud);
		hud.getSpriteBatch().begin();

		drawObjects();

		hud.getSpriteBatch().end();
	}

	private void drawObjects() {
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

		info.setPosition(800, Gdx.graphics.getHeight()-Gdx.input.getY()-100);
		info.draw(hud.getSpriteBatch());
	}

	private void updateObjects() {
		for (AbstractTower tower : m.getTowers()){
			if(tower.isShooting()){ //TODO Fix line under this, could be shorter
				bulletList.add(new Bullet(tower.getPosition().getX() + 45,tower.getPosition().getY() + 40,tower.getTargetPosition().getX()+27,tower.getTargetPosition().getY()+30,14f,new Texture("img/RedBullet.png")));
			}
		}

		if(waveIndex != m.getWaveIndex()) {
			for(int i = 0; i < m.getEnemies().size(); i++) {
				enemyList.add(new EnemyView( m.getEnemies().get(i)));
			}
			waveIndex = m.getWaveIndex();
		}

		hpLabel.setText("HP: " + m.getPlayer().getLives().getCurrentLives());
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

		//TODO Fix the color changer and maybe move this
		if(tmp != null) {
			tmp.setPosition(Gdx.input.getX()-tmp.getWidth()/2, Gdx.graphics.getHeight()-Gdx.input.getY()-tmp.getHeight()/2);
			radius.showRadius();
			radius.setPosition(tmp.getX(), tmp.getY());
		} else if(tmp == null && chosedTower == null) {
			radius.hideRadius();
		}
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

		final Image mapImg = new Image(new Texture("map/map.png"));
		final Image bt = new Image(new Texture("img/firstDragon.png"));
		final Image it = new Image(new Texture("img/iceDragon.png"));
		final Image mt = new Image(new Texture("img/hydra.png"));

		final TextButton upgradeBtn = new TextButton("Upgrade", uiSkin);
		final TextButton sellBtn = new TextButton("Sell", uiSkin);
		final TextButton nextWaveBtn = new TextButton("Next Wave", uiSkin);
		final TextButton cancelBuyBtn = new TextButton("Cancel Buy", uiSkin);

		Table guiTable = new Table();
		Table towerInfoTable = new Table();

		hud = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // OR
		hud.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(hud);

		ClickListener clickListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(event.getListenerActor() instanceof Image && event.getListenerActor() != mapImg) {
					if(tmp != null) {
						tmp.setVisible(false);
						tmp = null;				//TODO i dont think this is needed
					}
					String path = "";
					int rad = 0;
					if(event.getListenerActor() == bt) {
						path = "img/firstDragon.png";
						buildIndex = 1;
						rad = 200;
					} else if(event.getListenerActor() == it) {
						path = "img/iceDragon.png";
						buildIndex = 2;
						rad = 150;
					} else if(event.getListenerActor() == mt) {
						path = "img/hydra.png";
						buildIndex = 3;
						rad = 400;
					}
					tmp = new Image(new Texture(path));
					tmp.setPosition(Gdx.input.getX()-tmp.getWidth()/2, Gdx.graphics.getHeight()-Gdx.input.getY()-tmp.getHeight()/2);
					tmp.setTouchable(null);
					radius.setRadius(rad);
				} else if(event.getListenerActor() == mapImg) {
					if(tmp != null) {
						tmp.setVisible(false);		// TODO the image still exists under the tower, or does it?
						int tmpX = (int) (Gdx.input.getX()-tmp.getWidth()/2);
						int tmpY = (int) (Gdx.graphics.getHeight()-Gdx.input.getY()-tmp.getHeight()/2);
						tmp = null;
						boolean towerBuilt = false;
						switch(buildIndex) {		//TODO probably should do something else than this
						case 1:
							if(towerBuilt = m.buildTower(new BasicTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY))) {
								towerList.add(new TowerView(new Sprite(new Texture("img/firstDragon.png")), m.getTowers().get(towerIndex), towerIndex));
							}
							break;
						case 2:
							if(towerBuilt = m.buildTower(new IceTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY))) {
								towerList.add(new TowerView(new Sprite(new Texture("img/iceDragon.png")), m.getTowers().get(towerIndex), towerIndex));
							}
							break;
						case 3:
							if(towerBuilt = m.buildTower(new MultiTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) m.getEnemies()), new Position(tmpX, tmpY))) {
								towerList.add(new TowerView(new Sprite(new Texture("img/hydra.png")), m.getTowers().get(towerIndex), towerIndex));
							}
							break;
						default:
							System.out.println("No such tower exists"); 	//TODO debug
							break;
						}
						if(towerBuilt) {
							chosedTower = towerList.get(towerIndex);
							towerIndex++;
							buildIndex = 0;
						} else {
							radius.hideRadius();
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
				} else if(event.getListenerActor() instanceof TextButton) {
					if(event.getListenerActor() == sellBtn) {
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
					} else if(event.getListenerActor() == upgradeBtn) {
						if(chosedTower != null) {
							if(m.upgradeTower(chosedTower.getTower()))
								chosedTower.upgrade();
						}
					} else if(event.getListenerActor() == nextWaveBtn) {
						m.nextWave();
					} else if(event.getListenerActor() == cancelBuyBtn) {
						if(tmp != null) {
							tmp.setVisible(false);
							tmp = null;
						}
					}
				} 
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				if(event.getListenerActor() == bt || event.getListenerActor() == it || 
						event.getListenerActor() == mt) {
					int index = 0;
					if(event.getListenerActor() == bt) {
						index = 1;
					} else if(event.getListenerActor() == it) {
						index = 2;
					} else if(event.getListenerActor() == mt) {
						index = 3;
					}

					info.choseTower(index);
					info.show();
				}
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer,
					Actor toActor) {
				info.hide();
			}
		};	

		mapImg.addListener(clickListener);
		bt.addListener(clickListener);
		it.addListener(clickListener);
		mt.addListener(clickListener);
		upgradeBtn.addListener(clickListener);
		sellBtn.addListener(clickListener);
		nextWaveBtn.addListener(clickListener);
		cancelBuyBtn.addListener(clickListener);

		towerInfoTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		towerInfoTable.add(chosedTowerImage).left().row();
		towerInfoTable.add(towerName = new Label("Tower Name", uiSkin)).left().row();
		towerInfoTable.add(towerKills = new Label("Tower Name", uiSkin)).left().row();
		towerInfoTable.add(upgradeBtn).width(100).height(70).padBottom(20).padTop(20).padRight(20);
		towerInfoTable.add(sellBtn).width(100).height(70);

		Table buildTable = new Table();
		buildTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTest.png"))));
		buildTable.add(hpLabel = new Label("HP: " + m.getPlayer().getLives().getCurrentLives(), uiSkin)).padTop(10).row();
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
		guiTable.add(nextWaveBtn).width(200).height(60).padTop(5).padBottom(0).row();
		guiTable.add(cancelBuyBtn).width(200).height(60).padTop(0).padBottom(0);

		table = new Table();
		//table.debug();			//TODO debug
		table.add(mapImg);
		table.add(guiTable);
		table.setFillParent(true);
		table.bottom().left();

		hud.addActor(table);
	}

	@Override
	public void hide() { dispose();	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {	hud.dispose(); }

}