package com.me.teedee.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.me.teedee.Map;
import com.me.teedee.Path;
import com.me.teedee.PathFactory;
import com.me.teedee.Player;
import com.me.teedee.Position;
import com.me.teedee.WaveFactory;
import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.enemies.ShieldEnemy;
import com.me.teedee.towers.AbstractTower;
import com.me.teedee.towers.BasicTower;
import com.me.teedee.towers.BloodDragonTower;
import com.me.teedee.towers.IceTower;
import com.me.teedee.towers.MultiTower;
import com.me.teedee.towers.RNGTower;
import com.me.teedee.towers.ShockWaveTower;

/**
 * A screen that represents a the map with its
 * enemies and towers on the screen.
 * @author Daniel
 */
public class MapScreen implements Screen {
	// TODO Make all possible variables more local!!!
	private Map map;
	private Stage hud;
	private Table table;
	private Table guiTable;
	
	private int difficulty;
	private int pathChoice;
	
	private Label towerName;
	private Label towerKills;
	private Label moneyLabel;
	private Label hpLabel;
	private Label waveLabel;

	private InfoImage info;
	private RadiusImage radius;
	private Image chosedTowerImage;
	private TowerView chosedTower;

	private int towerIndex = 0;		// TODO change this shit, maybe not, i dont know
	private int waveIndex = 0;
	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private List<TowerView> towerList = new ArrayList<TowerView>();
	private List<EnemyView> enemyList = new ArrayList<EnemyView>();
	private List<Notification> notificationList = new ArrayList<Notification>();

	private Sprite[] tiledPath;

	private Image tmp;						// TODO tmp varaible, should probably create new class to handle this

	private float ratio = 1;
	private int buildIndex = 0;		//	^this
	FPSLogger fps = new FPSLogger();		// TODO debug
	private boolean soundIsOn = true;

	private List<Sound> dyingSoundList = new ArrayList<Sound>();
	private List<Sound> shootingSoundList = new ArrayList<Sound>();
	private Texture soundOnTexture = new Texture("data/speaker_louder_32.png");
	private Texture soundOffTexture = new Texture("data/speaker_off_32.png");

	private String mapPath;

	int k = 0; 		// logic for radius color changer
	int l = 0;		

	public MapScreen(int difficulty, int pathChoice, String mapPath) {
		this.mapPath = mapPath;
		this.difficulty = difficulty;
		this.pathChoice = pathChoice;
		
		//Adding sounds for shooting
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot0.wav")));
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot1.wav")));
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot2.wav")));
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot3.wav")));
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot4.wav")));
		//FIXME
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot0.wav")));
		shootingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/shot5.wav")));
		// Adding sounds for dying
		dyingSoundList.add(Gdx.audio.newSound(Gdx.files.internal("data/WilhelmScream_64kb.mp3")));

		//Creating the path
		Path path = PathFactory.createPath(pathChoice);

		//Creating a player
		Player player = new Player();

		//Creating the map
		map = new Map(WaveFactory.createWave(difficulty,path), path, player);

		PathView pv = new PathView(map.getPath().getPositions());

		tiledPath = pv.getSprites();
		chosedTowerImage = new Image(new Texture("img/unknown.png"));
		radius = new RadiusImage(new Texture("img/radius200.png"));
		info = new InfoImage();
		guiTable = new Table();
	}

	@Override
	public void render(float delta) {
		//fps.log();		// TODO debug
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(map.isRunning()){
			map.update(delta);
			updateObjects();
		}

		hud.act(delta);
		hud.draw();
		Table.drawDebug(hud);		//TODO debug

		if(map.isRunning()){
			hud.getSpriteBatch().begin();
			drawObjects();
			hud.getSpriteBatch().end();
		}
	}

	private void drawObjects() {
		for(int i=0; i<map.getPath().getPositions().size()-1; i++){//As of now renders the path somewhat, should probably not be an sprite. If possible use another more suitable class.  
			tiledPath[i].draw(hud.getSpriteBatch());
		}

		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).draw(hud.getSpriteBatch());
			if(!enemyList.get(i).isAlive() || enemyList.get(i).reachedEnd()){
				enemyList.get(i).setAlpha(0);
				if(!enemyList.get(i).isAlive() && !enemyList.get(i).reachedEnd()) {
					notificationList.add(new Notification("$" + enemyList.get(i).getReward(), enemyList.get(i).getX(), enemyList.get(i).getY()));
					if(soundIsOn)
						playDyingSound(0);
				} else {
					//TODO wrong location
					notificationList.add(new Notification("-1", hpLabel.getX(), hpLabel.getY()));
				}
				enemyList.remove(i);
			}
		}

		for(int i = 0; i < notificationList.size(); i++) {
			notificationList.get(i).draw(hud.getSpriteBatch());
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
		//TODO row is too long
		info.setPosition(guiTable.getX()-info.getWidth()*1.1f, (Gdx.graphics.getHeight()-Gdx.input.getY()-info.getHeight()/2/ratio)*ratio);
		info.draw(hud.getSpriteBatch());
	}

	private void playShootingSound(int index){
		shootingSoundList.get(index).play();
	}

	private void playDyingSound(int index){
		dyingSoundList.get(index).play();
	}

	private void updateObjects() {
		if(!map.isPlayerAlive()){
			((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen());
		}

		for (AbstractTower tower : map.getTowers()){
			if(tower.isShooting()){			 //TODO Fix line under this, could be shorter
				for(Position p: tower.getTargetPosition()){
					bulletList.add(new Bullet(p , 14f, tower));
				}
				if(soundIsOn)
					playShootingSound(tower.getId());
			}
		}

		if(waveIndex != map.getWaveIndex()) {
			for(int i = 0; i < map.getEnemies().size(); i++) {
				if( map.getEnemies().get(i) instanceof ShieldEnemy){
					enemyList.add(new ShieldEnemyView((ShieldEnemy) map.getEnemies().get(i)));	
				}else{
					enemyList.add(new EnemyView( map.getEnemies().get(i)));
				}
			}
			waveIndex = map.getWaveIndex();
		}

		for(int i = 0; i < notificationList.size(); i++) {
			if(notificationList.get(i).isDone()) {
				notificationList.remove(i);
			}
		}

		hpLabel.setText("HP: " + (int)map.getPlayer().getLives().getCurrentLives());
		moneyLabel.setText("$ " + map.getPlayer().getMoneyInt());
		waveLabel.setText("Wave: " + map.getWaveIndex());

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
			//TODO maybe change this row
			tmp.setPosition((Gdx.input.getX()-tmp.getWidth()/2/ratio)*ratio, (Gdx.graphics.getHeight()-Gdx.input.getY()-tmp.getHeight()/2/ratio)*ratio);
			radius.showRadius();
			radius.setPosition(tmp.getX(), tmp.getY());
			
			//FIXME Change width and height to correct numbers
			//TODO maybe change this to a for loop instead and see if performance still is good
			Rectangle tmpRect = new Rectangle(0, 0, 40, 40);
			tmpRect.setCenter(Gdx.input.getX()*ratio, Gdx.graphics.getHeight()-Gdx.input.getY()*ratio);
			if(tiledPath[l].getBoundingRectangle().overlaps(tmpRect)) {
				radius.setColorRed();
			} else {
				radius.setColorDefault();
			}
			
			if(!radius.isRed()) {
				l++;
				if(l >= tiledPath.length -1) {
					l = 0;
				}
			}

			//TODO this needs optimizing or done in another way
			if(k >= towerList.size()) {
				k = 0;
			}
			//TODO maybe change this to a for loop instead and see if performance still is good
			if(!towerList.isEmpty() && !radius.isRed()) {
				float dx = tmp.getX() - towerList.get(k).getX();
				float dy = tmp.getY() - towerList.get(k).getY();
				double d =  Math.sqrt(dx*dx+dy*dy);
				if(d < 40) {
					radius.setColorRed();
				} else {
					radius.setColorDefault();
				}

				if(!radius.isRed()) {
					k++;
					if(k >= towerList.size()) {
						k = 0;
					}
				}
			}

		} else if(tmp == null && chosedTower == null) {
			radius.hideRadius();
		}
	}

	@Override
	public void resize(int width, int height) {
		hud.getViewport().update(width, height, true);
		table.invalidateHierarchy();
		ratio = hud.getHeight()/Gdx.graphics.getHeight();
		System.out.println("gui" + guiTable.getWidth() + " " + guiTable.getHeight());
	}

	public TowerView clickedOnTower(float x, float y) {
		for(TowerView tv : towerList) {
			if(tv.contains(Gdx.input.getX()*ratio, (Gdx.graphics.getHeight()-Gdx.input.getY())*ratio)) {
				return tv;
			}
		}
		return null;	
	}

	@Override
	public void show() {
		Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		final Image mapImg = new Image(new Texture(mapPath));
		final Image bt = new Image(new Texture("img/firstDragon.png"));
		final Image it = new Image(new Texture("img/iceDragon.png"));
		final Image mt = new Image(new Texture("img/hydra.png"));
		final Image swt = new Image(new Texture("img/shockwave.png"));
		final Image rng = new Image(new Texture("img/RNGTower.png"));
		final Image bdt = new Image(new Texture("img/bloodDragon.png"));

		final TextButton upgradeBtn = new TextButton("Upgrade", uiSkin);
		final TextButton sellBtn = new TextButton("Sell", uiSkin);
		final TextButton nextWaveBtn = new TextButton("Next Wave", uiSkin);
		final TextButton cancelBuyBtn = new TextButton("Cancel Buy", uiSkin);
		final TextButton pauseBtn = new TextButton("Pause", uiSkin);
		final TextButton resumeButton =  new TextButton("Resume Game", uiSkin);
		final TextButton quitButton = new TextButton("Quit Game", uiSkin);
		final TextButton resetButton = new TextButton("Reset Game", uiSkin);


		final Button soundButton = new Button(uiSkin);
		soundButton.add(new Image(soundOnTexture));

		Table towerInfoTable = new Table();
		Table buildTable = new Table();
		Table buttonTable = new Table();
		Table towerButtons = new Table();

		hud = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); // OR
		hud.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(hud);

		final Window pauseWindow = new Window("", uiSkin);
		pauseWindow.setVisible(false);
		pauseWindow.setMovable(false);
		pauseWindow.sizeBy(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
		pauseWindow.setY(Gdx.graphics.getHeight()/7f);
		pauseWindow.setX(Gdx.graphics.getWidth()/15f);

		ClickListener clickListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(MapScreen.this.map.isRunning()){
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
							rad = 300;
						} else if(event.getListenerActor() == swt) {
							path = "img/shockwave.png";
							buildIndex = 4;
							rad = 350;
						} else if(event.getListenerActor() == rng) {
							path = "img/RNGTower.png";
							buildIndex = 5;
							rad = 280;
						} else if(event.getListenerActor() == bdt) {
							path = "img/bloodDragon.png";
							buildIndex = 6;
							rad = 200;
						}

						tmp = new Image(new Texture(path));
						tmp.setPosition(Gdx.input.getX()-tmp.getWidth()/2, Gdx.graphics.getHeight()-Gdx.input.getY()-tmp.getHeight()/2);
						tmp.setTouchable(null);
						radius.setRadius(rad);
					} else if(event.getListenerActor() == mapImg) {
						if(tmp != null) {
							tmp.setVisible(false);		// TODO the image still exists under the tower, or does it?

							//TODO maybe change these two rows under
							int tmpX = (int) ((Gdx.input.getX()-tmp.getWidth()/2/ratio)*ratio);
							int tmpY = (int) ((Gdx.graphics.getHeight()-Gdx.input.getY()-tmp.getHeight()/2/ratio)*ratio);
							tmp = null;
							boolean towerBuilt = false;
							switch(buildIndex) {		//TODO probably should do something else than this
							case 1:
								if(towerBuilt = map.buildTower(new BasicTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) map.getEnemies()), new Position(tmpX, tmpY))) {
									towerList.add(new TowerView(new Sprite(new Texture("img/firstDragon.png")), map.getTowers().get(towerIndex), towerIndex));
								}
								break;
							case 2:
								if(towerBuilt = map.buildTower(new IceTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) map.getEnemies()), new Position(tmpX, tmpY))) {
									towerList.add(new TowerView(new Sprite(new Texture("img/iceDragon.png")), map.getTowers().get(towerIndex), towerIndex));
								}
								break;
							case 3:
								if(towerBuilt = map.buildTower(new MultiTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) map.getEnemies()), new Position(tmpX, tmpY))) {
									towerList.add(new TowerView(new Sprite(new Texture("img/hydra.png")), map.getTowers().get(towerIndex), towerIndex));
								}
								break;
							case 4:
								if(towerBuilt = map.buildTower(new ShockWaveTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) map.getEnemies()), new Position(tmpX, tmpY))) {
									towerList.add(new TowerView(new Sprite(new Texture("img/shockwave.png")), map.getTowers().get(towerIndex), towerIndex));
								}
								break;
							case 5:
								if(towerBuilt = map.buildTower(new RNGTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) map.getEnemies()), new Position(tmpX, tmpY))) {
									towerList.add(new TowerView(new Sprite(new Texture("img/RNGTower.png")), map.getTowers().get(towerIndex), towerIndex));
								}
								break; 
							case 6:
								if(towerBuilt = map.buildTower(new BloodDragonTower(new Position(tmpX, tmpY), (ArrayList<AbstractEnemy>) map.getEnemies()), new Position(tmpX, tmpY))) {
									towerList.add(new TowerView(new Sprite(new Texture("img/bloodDragon.png")), map.getTowers().get(towerIndex), towerIndex));
								}
								break;
							default:
								System.out.println("No such tower exists"); 	//TODO debug
								break;
							}
							if(towerBuilt) {
								map.getTowers().get(towerIndex).setIndex(towerIndex);
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
								radius.setColorDefault();
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
								map.sellTower(tmpIndex);
								chosedTower.setAlpha(0);
								notificationList.add(new Notification("$" + chosedTower.getValue(), chosedTower.getX(), chosedTower.getY()));
								towerList.remove(tmpIndex);
								for(int i = 0; i < map.getTowers().size(); i++) {
									map.getTowers().get(i).setIndex(i);
								}
								for(int i = 0; i < towerList.size(); i++) {
									TowerView tmp = towerList.get(i);
									tmp.setIndex(tmp.getTower().getIndex());
								}
								towerIndex = towerList.size();
								chosedTower = null;
							}
						} else if(event.getListenerActor() == upgradeBtn) {
							if(chosedTower != null) {
								int tmpValue = chosedTower.getUpgradePrice();
								if(map.upgradeTower(chosedTower.getTower())) {
									notificationList.add(new Notification("-$" + tmpValue, chosedTower.getX(), chosedTower.getY()));
									chosedTower.upgrade();
								}
							}
						} else if(event.getListenerActor() == nextWaveBtn) {
							map.nextWave();
						} else if(event.getListenerActor() == cancelBuyBtn) {
							if(tmp != null) {
								tmp.setVisible(false);
								tmp = null;
								radius.hideRadius();
							}
						}
					}
				} 
				if(event.getListenerActor() == soundButton){
					if(soundIsOn){
						soundIsOn = false;
						soundButton.clearChildren();
						soundButton.add(new Image(soundOffTexture));
					} else{
						soundIsOn = true;
						soundButton.clearChildren();
						soundButton.add(new Image(soundOnTexture));
					}
				}
				if(event.getListenerActor().equals(pauseBtn)){
					if(MapScreen.this.map.isRunning()){
						MapScreen.this.pause();
						pauseWindow.setVisible(true);

					}else{
						MapScreen.this.resume();
						pauseWindow.setVisible(false);
					}
				}
				if(event.getListenerActor().equals(resumeButton)){
					MapScreen.this.resume();
					pauseWindow.setVisible(false);
				}else if(event.getListenerActor().equals(quitButton)){
					((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
				}else if(event.getListenerActor().equals(resetButton)){
					((Game) Gdx.app.getApplicationListener()).setScreen(new MapScreen(MapScreen.this.difficulty,MapScreen.this.pathChoice,MapScreen.this.mapPath));
				}
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				if(event.getListenerActor() instanceof Image) {
					int index = -1;
					if(event.getListenerActor() == bt) {
						index = 1;
					} else if(event.getListenerActor() == it) {
						index = 2;
					} else if(event.getListenerActor() == mt) {
						index = 3;
					} else if(event.getListenerActor() == swt) {
						index = 4;
					} else if(event.getListenerActor() == rng) {
						index = 5;
					} else if(event.getListenerActor() == bdt) {
						index = 6;
					}

					if(index != -1) {
						info.choseTower(index);
						info.show();
					}
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
		swt.addListener(clickListener);
		bdt.addListener(clickListener);
		rng.addListener(clickListener);
		upgradeBtn.addListener(clickListener);
		sellBtn.addListener(clickListener);
		nextWaveBtn.addListener(clickListener);
		cancelBuyBtn.addListener(clickListener);
		pauseBtn.addListener(clickListener);
		soundButton.addListener(clickListener);
		resumeButton.addListener(clickListener);
		quitButton.addListener(clickListener);
		resetButton.addListener(clickListener);
		
		pauseWindow.add(resumeButton).width(200).height(50).spaceBottom(30f).center().row();
		pauseWindow.add(resetButton).width(200).height(50).spaceBottom(30f).row();
		pauseWindow.add(quitButton).width(200).height(50);
		

		towerButtons.add(upgradeBtn).width(100).height(70).padBottom(20).padTop(20).padRight(20);
		towerButtons.add(sellBtn).width(100).height(70).left();

		towerInfoTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTable.png"))));
		towerInfoTable.add(chosedTowerImage).left().row();
		towerInfoTable.add(towerName = new Label("Tower Name", uiSkin)).left().row();
		towerInfoTable.add(towerKills = new Label("Tower Name", uiSkin)).left().row();
		towerInfoTable.add(towerButtons);
		//towerInfoTable.debug();		// TODO debug

		buildTable.setBackground(new SpriteDrawable(new Sprite(new Texture("img/buildTable.png"))));
		buildTable.add(hpLabel = new Label("HP: " + map.getPlayer().getLives().getCurrentLives(), uiSkin)).padTop(10).left().padLeft(40).row();
		buildTable.add(moneyLabel = new Label("$ " + map.getPlayer().getMoneyInt(), uiSkin)).padLeft(40).left().row();
		buildTable.add(waveLabel = new Label("Wave: " + map.getWaveIndex(), uiSkin)).padBottom(30).padLeft(40).left().row();
		buildTable.add(bt).top().padLeft(20);
		buildTable.add(it);
		buildTable.add(mt).padRight(20).row();
		buildTable.add(swt).padLeft(20);
		buildTable.add(rng);
		buildTable.add(bdt).padRight(20);
		buildTable.top();
		//buildTable.debug();		//TODO debug

		buttonTable.add(nextWaveBtn).width(200).height(60).padTop(5);
		buttonTable.add(pauseBtn).width(60).height(60).padTop(5).row();
		buttonTable.add(cancelBuyBtn).width(200).height(60).padTop(0);
		buttonTable.add(soundButton).width(60).height(60).padTop(0).row();

		guiTable.add(buildTable).row();
		guiTable.add(towerInfoTable).width(315).row();
		guiTable.add(buttonTable);
		//guiTable.debug();		//TODO debug;

		table = new Table();
		table.add(mapImg);
		table.add(guiTable);
		table.setFillParent(true);
		table.bottom().left();
		//table.debug();			//TODO debug

		hud.addActor(table);
		hud.addActor(pauseWindow);
	}

	@Override
	public void hide() { dispose();	}

	@Override
	public void pause() {
		MapScreen.this.map.setRunning(false);
	}

	@Override
	public void resume() { 
		MapScreen.this.map.setRunning(true);
	}

	@Override
	public void dispose() {	
		hud.dispose();
		for(Sound sound : shootingSoundList){
			sound.dispose();
		}
	}

}