package com.me.teedee.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.Tower;

public class TowerView extends Sprite {
	private Tower tower;
	
	public TowerView(Sprite sprite, Tower tower){
		super(sprite);
		this.tower = tower;
		setX(tower.getPosition().getX());
		setY(tower.getPosition().getY());
	}
	
	public void draw(Batch batch){
		if(tower.isShooting()){
			//TODO
		}
			
	}
	
	
	
}
