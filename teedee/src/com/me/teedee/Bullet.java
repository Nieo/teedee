package com.me.teedee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite{
	private Position targetPosition;
	private Position startPosition;
	private Vector2 direction;
	private float speed;
	
	
	public Bullet(float startX, float startY, float targetX, float targetY, float speed, Texture texture){
		this(new Position(startX,startY),new Position (targetX,targetY), speed, texture);

	}
	
	public Bullet(Position startPosition, Position targetPosition, float speed, Texture texture){
		super(new Sprite(texture));
		this.startPosition = startPosition;
		this.targetPosition = targetPosition;
		this.direction = new Vector2(targetPosition.getX() - startPosition.getX(),
									 targetPosition.getY() - startPosition.getY()).nor();
		this.speed = speed;
	}
	
	public void update(){
		this.setPosition(getX() + speed*direction.x, getY() + speed*direction.y);
	}
	
	@Override
	public void draw(Batch batch) {
		update();
		super.draw(batch);
	}

}
