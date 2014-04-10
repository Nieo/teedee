package com.me.teedee.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.Tower;

public class TowerView extends Sprite {
	private Tower tower;
	Vector2 vector;
	
	public TowerView(Sprite sprite, Tower tower) {
		super(sprite);
		vector = new Vector2(tower.getPosition().getX(), tower.getPosition().getY());
		this.tower = tower;
		System.out.println(tower.getPosition().getX()+" "+tower.getPosition().getY());
		setX(tower.getPosition().getX());
		setY(tower.getPosition().getY());
	}
	
	public void draw(Batch batch) {
		vector.sub(tower.getTargetPosition().getX(), tower.getTargetPosition().getY());
		setRotation(vector.angle());
		
		super.draw(batch);
		if(tower.isShooting()) {
			//TODO
		}
			
	}
	
	
	
}
