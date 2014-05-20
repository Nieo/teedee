package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.enemies.AbstractEnemy;

/**
 * A view that represents a enemy on the screen.
 * @author Daniel
 */

public class EnemyView extends Sprite {

	private AbstractEnemy enemy;
	private Sprite green;
	private Sprite red;

	public EnemyView(AbstractEnemy abstractEnemy) {
		int swID = abstractEnemy.getId();
		String path = "img/firstEnemy.png";
		switch(swID){
		case 1: 
			path = "img/firstEnemy.png";
			break;
		case 2:
			path = "img/fastEnemy.png";
			break;
		case 3:
			path = "img/slowEnemy.png";
			break;
		case 4:
			path = "img/hydra3.png";
			break;
		default:
			path = "img/firstEnemy.png";
			break;
		}
		this.set(new Sprite(new Texture(path)));

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
		setX(enemy.getPosition().getX());
		setY(enemy.getPosition().getY());
		green.setX(getX());
		green.setY(getY()+getHeight());
		float tmpHP = enemy.getLives().getCurrentLives();
		float maxHP = enemy.getLives().getMaxLives();
		if(tmpHP/maxHP < 0) {
			green.setSize(0, red.getHeight());
		} else {
			green.setSize(red.getWidth()*(tmpHP/maxHP), red.getHeight());
		}
		red.setX(getX());
		red.setY(getY()+getHeight());
	}

	public boolean reachedEnd() {
		return enemy.reachedEnd();
	}

	public int getReward() {
		return enemy.getEnemyReward().getReward();
	}
	
	public float getPositionX() {
		return enemy.getPosition().getX();
	}
	
	public float getPositionY() {
		return enemy.getPosition().getY();
	}
}
