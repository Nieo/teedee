package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.AbstractTower;

public class TowerView extends Sprite {
	private AbstractTower tower;
	private Vector2 vector;
	private float posX;
	private float posY;
	private String name;
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
		//this.name = tower.getType();		//TODO or getName(); or something similiar
		
		switch(tower.getId())  {
		case 1:
			this.name = "Basic Tower";
			break;
		case 2:
			this.name = "Another Tower";		//TODO
			break;
			// osv
		}

	}
	
	public boolean contains(float x, float y) {
		return rect.contains(x, y);
	}
	
	public void draw(Batch batch) {
		//TODO not sure if this is needed
		vector.set(posX, posY);
		
		vector.sub(tower.getTargetPosition().getX(), tower.getTargetPosition().getY());
		vector.nor();
		setRotation(vector.angle()+90);
		
		if(tower.isShooting()) {
			//TODO
		}
		
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

	public void sell() {
		//tower.sell();		//TODO no sellmethod in tower class
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
	
}
