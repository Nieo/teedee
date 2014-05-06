package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.enemies.AbstractEnemy;

//import com.me.teedee.BasicEnemy;

/**
 * A view that represents a enemy on the screen.
 * @author Daniel
 */

public class EnemyView extends Sprite {
	
	private AbstractEnemy enemy;
	private float width;
	private float height;
	private Sprite green;
	private Sprite red;
	
	public EnemyView(Sprite sprite, AbstractEnemy abstractEnemy) {
		super(sprite);
		height = sprite.getHeight();
		width = sprite.getWidth();
		this.enemy = abstractEnemy;
		green = new Sprite(new Texture("img/green.png"));
		red = new Sprite(new Texture("img/red.png"));
	}

	@Override
	public void draw(Batch batch) {
		update();
		super.draw(batch);
		red.draw(batch);
		green.draw(batch);
	}
	
	public boolean isAlive(){
		return enemy.isAlive();
	}
	
	//FIXME probably should clean the method up a bit
	private void update() {
		setX(enemy.getPosition().getX()+width/2);
		setY(enemy.getPosition().getY()+height/2);
		green.setX(getX());
		green.setY(getY()+getHeight());
		float tmpHP = enemy.getLives().getLivesHealth();
		float maxHP = enemy.getLives().getMaxLives();
		green.setSize(red.getWidth()*(tmpHP/maxHP), red.getHeight());
		red.setX(getX());
		red.setY(getY()+getHeight());
	}

	public boolean reachedEnd() {
		return enemy.reachedEnd();
	}
}
