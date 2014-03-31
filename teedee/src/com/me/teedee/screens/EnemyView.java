package com.me.teedee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.BasicEnemy;

public class EnemyView extends Sprite {

	private float speed = 60 * 3f;		// FIXME delete this and replace this with enemy.getSpeed()
										// or set speed = enemy.getSpeed() in constructor
	private BasicEnemy enemy;

	private Vector2 vector;
	private TiledMapTileLayer collisionLayer;

	public EnemyView(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		vector = new Vector2();
		this.collisionLayer = collisionLayer;
		vector.x = speed;
		vector.y = 0;
		
		// enemy = new BasicEnemy();
		// speed = enemy.getSpeed(); 		//TODO maybe do this
	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	// I'm not sure this is working as it should
	public boolean changeDirection(float x, float y, String direction) {			// FIXME maybe this should be private
		return collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()))
				.getTile().getProperties().containsKey(direction);
	}

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
		
		if(vector.x < 0) {
			moveUp = changeDirection(getX(), getY(), "up");

			if(!moveUp)
				moveDown = changeDirection(getX(), getY(), "down");
		} else if (vector.x > 0) {
			moveUp = changeDirection(getX(), getY(), "up");

			if(!moveUp)
				moveDown = changeDirection(getX(), getY(), "down");
		}

		if(vector.y < 0) {
			moveRight = changeDirection(getX(), getY(), "right");

			if(!moveRight)
				moveLeft = changeDirection(getX(), getY(), "left");
		} else if(vector.y > 0) {			
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
