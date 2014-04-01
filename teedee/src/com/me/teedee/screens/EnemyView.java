package com.me.teedee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

//import com.me.teedee.BasicEnemy;

/**
 * A view that represents a enemy on the screen.
 * @author Daniel
 */

public class EnemyView extends Sprite {
	
	// FIXME delete this and replace this with enemy.getSpeed()
	// or set speed = enemy.getSpeed() in constructor
	private float speed;
	
	//private BasicEnemy enemy;

	private Vector2 vector;
	private TiledMapTileLayer collisionLayer;

	public EnemyView(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		vector = new Vector2();
		this.collisionLayer = collisionLayer;
		speed = 60 * 3f;	// speed = enemy.getSpeed(); 		//TODO maybe do this or something similar
		vector.x = speed;
		vector.y = 0;
		
		// TODO här behöver vi veta vilken typ av enemy det är
		// ifall vi tex istället vill skriva: enemy = new FastEnemy();
		// enemy = new BasicEnemy();
	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	//FIXME I'm not sure this is working as it should
	// FIXME maybe this should be private
	public boolean changeDirection(float x, float y, String direction) {
		return collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()))
				.getTile().getProperties().containsKey(direction);
	}
	
	
	//FIXME probably should clean the method up a bit
	private void update(float delta) {
		boolean moveUp = false;
		boolean moveDown = false;
		boolean moveRight = false;
		boolean moveLeft = false;


		if(vector.x > speed) {
			vector.x = speed;
		} else if(vector.x < -speed) {
			vector.x = -speed;
		}

		setX(getX() + vector.x * delta);
		setY(getY() + vector.y * delta);
		
		if(vector.x != 0) {
			moveUp = changeDirection(getX(), getY(), "up");

			if(!moveUp)
				moveDown = changeDirection(getX(), getY(), "down");
		}

		if(vector.y != 0) {
			moveRight = changeDirection(getX(), getY(), "right");

			if(!moveRight)
				moveLeft = changeDirection(getX(), getY(), "left");
		}


		if(moveUp) {
			vector.x = 0;
			vector.y = speed;
		} else if(moveDown) {
			vector.x = 0;
			vector.y = -speed;
		} else if(moveRight) {
			vector.x = speed;
			vector.y = 0;
		} else if(moveLeft) {
			vector.x = -speed;
			vector.y = 0;
		}
	}
}
