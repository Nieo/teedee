package com.me.teedee.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.AbstractEnemy;

//import com.me.teedee.BasicEnemy;

/**
 * A view that represents a enemy on the screen.
 * @author Daniel
 */

public class EnemyView extends Sprite {
	
	private AbstractEnemy enemy;
	private float width;
	private float height;
	public EnemyView(Sprite sprite, AbstractEnemy abstractEnemy) {
		super(sprite);
		height = sprite.getHeight();
		width = sprite.getWidth();
		this.enemy = abstractEnemy;
	}

	@Override
	public void draw(Batch batch) {
		update();
		super.draw(batch);
	}
	
	public boolean isAlive(){
		return enemy.isAlive();
	}
	
	//FIXME probably should clean the method up a bit
	private void update() {
		setX(enemy.getPosition().getX()-width/2);
		setY(enemy.getPosition().getY()-height/2);
	}

	public boolean reachedEnd() {
		return enemy.reachedEnd();
	}
}
