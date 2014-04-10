package com.me.teedee.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.Tower;

public class TowerView extends Sprite {
	private Tower tower;
	Vector2 vector;
	private float posX;
	private float posY;
	
	public TowerView(Sprite sprite, Tower tower) {
		super(sprite);
		this.tower = tower;
		posX = this.tower.getPosition().getX();
		posY = this.tower.getPosition().getY();
		vector = new Vector2(posX, posY);
		setX(posX);
		setY(posY);
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
	
	
	
}
