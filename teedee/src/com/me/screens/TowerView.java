package com.me.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.towers.AbstractTower;
/**
 * A class that graphically represent a tower unit.
 */
public class TowerView extends Sprite {
	private AbstractTower tower;
	private String name;
	private float posX;
	private float posY;
	private float angle;
	private Vector2 vector;
	private int index;
	public Rectangle rect;

	public TowerView(Sprite sprite, AbstractTower tower, int index) {
		super(sprite);
		this.tower = tower;
		this.index = index;
		posX = this.tower.getPosition().getX();
		posY = this.tower.getPosition().getY();
		vector = new Vector2(posX, posY);
		setOriginCenter();
		setX(posX);
		setY(posY);
		rect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		
		this.name = tower.getName();

	}

	public boolean contains(float x, float y) {
		return rect.contains(x, y);
	}

	@Override
	public void draw(Batch batch) {
		
		vector.set(posX, posY);

		if(!tower.getTargetPosition().isEmpty() && 
				tower.getTargetPosition().get(0).getX() != 0 && 
				tower.getTargetPosition().get(0).getY() != 0) {
			vector.sub(tower.getTargetPosition().get(0).getX(), tower.getTargetPosition().get(0).getY());
			vector.nor();
			angle = vector.angle() + 90;
		}
		setRotation(angle);

		super.draw(batch);
	}

	public int getKills() {
		return tower.getKills();
	}

	public String getName() {
		return name;
	}
	
	public AbstractTower getTower(){
		return tower;
	}

	public void upgrade() {
		switch(tower.getId()) {
		case 1:
			upgradeBasicTower();
			break;
		case 2:
			upgradeIceTower();
			break;
		case 3:
			upgradeMultiTower();
			break;
		case 4:
			upgradeShockWaveTower();
			break;
		case 5:
			upgradeRNGTower();
			break;
		case 6:
			upgradeBloodDragon();
			break;
		default:
		}
	}

	//TODO change images
	private void upgradeRNGTower() {
		String picPath = "";
		switch(tower.getCurrentLevel()) {
		case 1:
			picPath = "img/RNGTower.png";
			break;
		case 2:
			picPath = "img/RNGTower.png";
			break;
		case 3:
			picPath = "img/RNGTower.png";
			break;
		case 4:
			picPath = "img/RNGTower.png";
			break;
		default:
			picPath = "img/RNGTower.png";
		}
		super.setTexture(new Texture(picPath));		
	}


	private void upgradeBloodDragon() {
		String picPath = "";
		switch(tower.getCurrentLevel()) {
		case 1:
			picPath = "img/bloodDragon1.png";
			break;
		case 2:
			picPath = "img/bloodDragon1.png";
			break;
		case 3:
			picPath = "img/bloodDragon2.png";
			break;
		case 4:
			picPath = "img/bloodDragon2.png";
			break;
		default:
			picPath = "img/bloodDragon.png";
		}
		super.setTexture(new Texture(picPath));		
	}

	private void upgradeShockWaveTower() {
		String picPath = "";
		switch(tower.getCurrentLevel()) {
		case 1:
			picPath = "img/shockwave.png";
			break;
		case 2:
			picPath = "img/shockwave.png";
			break;
		case 3:
			picPath = "img/shockwave.png";
			break;
		case 4:
			picPath = "img/shockwave.png";
			break;
		default:
			picPath = "img/shockwave.png";
		}
		super.setTexture(new Texture(picPath));
	}

	private void upgradeIceTower() {
		String picPath = "";
		switch(tower.getCurrentLevel()) {
		case 1:
			picPath = "img/iceDragon1.png";
			break;
		case 2:
		case 3:
		case 4:
			picPath = "img/iceDragon2.png";
			break;
		default:
			picPath = "img/iceDragon.png";
		}
		super.setTexture(new Texture(picPath));		
	}

	private void upgradeBasicTower() {
		String picPath = "";
		switch(tower.getCurrentLevel()) {
		case 1:
			picPath = "img/firstDragon1.png";
			break;
		case 2:
			picPath = "img/firstDragon2.png";
			break;
		case 3:
			picPath = "img/firstDragon3.png";
			break;
		case 4:
			picPath = "img/firstDragon4.png";
			break;
		default:
			picPath = "img/firstDragon.png";
		}
		super.setTexture(new Texture(picPath));
	}
	//TODO change images 2,4 and 6 heads 
	private void upgradeMultiTower() {
		String picPath = "";
		switch(tower.getCurrentLevel()) {
		case 1:
			picPath = "img/hydra3.png";
			break;
		case 2:
			picPath = "img/hydra3.png";
			break;
		case 3:
			picPath = "img/hydra5.png";
			break;
		case 4:
			picPath = "img/hydra5.png";
			break;
		default:
			picPath = "img/hydra.png";
		}
		super.setTexture(new Texture(picPath));
	}

	public int getCurrentLevel() {
		return tower.getCurrentLevel();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getId() {
		return tower.getId();
	}

	public int getUpgradePrice() {
		return tower.getUpgradePrice().getPrice();
	}

	public int getValue() {
		return (int) tower.getSellValue();
	}
}
