package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.Position;
import com.me.teedee.towers.AbstractTower;

public class Bullet extends Sprite{
	private Position targetPosition;
	private Position startPosition;
	private Vector2 direction;
	private float speed;
	private float travelledDistance = 0;
	private double goalDistance;
	private boolean hasHitTarget = false;


	public Bullet(float startX, float startY, float targetX, float targetY, float speed, Texture texture){
		this(new Position(startX,startY),new Position (targetX,targetY), speed, texture);

	}

	public Bullet(float targetX, float targetY, float speed, AbstractTower t){
		super(new Texture("img/IceBullet.png"));
		switch(t.getId()) {
		case 1:
			setRegion(new Texture("img/fireBullet.png"));
			break;
		case 2:
			setTexture(new Texture("img/IceBullet.png"));
			break;
			//osv
		default:
			setTexture(new Texture("img/RedBullet.png"));
		}
		startPosition = new Position(t.getPosition().getX() + 45 - getWidth()/2, t.getPosition().getY() + 40 - getHeight()/2);
		targetPosition = new Position(targetX, targetY);
		this.direction = new Vector2(targetPosition.getX() - startPosition.getX(),
				targetPosition.getY() - startPosition.getY()).nor();
		setOrigin(getX() + getWidth()/2, getY() + getHeight()/2);
		setRotation(direction.angle()+90);
		this.speed = speed;
		this.goalDistance = Math.sqrt((targetPosition.getX()-getX()) + (targetPosition.getY()-getY()));
		this.setPosition(startPosition.getX(), startPosition.getY());
		
	}

	public Bullet(Position startPosition, Position targetPosition, float speed, Texture texture){
		super(new Sprite(texture));
		this.startPosition = startPosition;
		this.targetPosition = targetPosition;
		this.direction = new Vector2(targetPosition.getX() - startPosition.getX(),
				targetPosition.getY() - startPosition.getY()).nor();
		this.speed = speed;
		this.goalDistance = Math.sqrt((targetPosition.getX()-getX()) + (targetPosition.getY()-getY()));
		this.setPosition(startPosition.getX(), startPosition.getY());
	}

	public void update(){
		travelledDistance += speed;
		this.setPosition(getX() + speed*direction.x, getY() + speed*direction.y);

		if(travelledDistance >= goalDistance + 80){
			this.setAlpha(0);
			hasHitTarget = true;
		}
	}

	public boolean hasHitTarget(){
		return hasHitTarget;
	}

	@Override
	public void draw(Batch batch) {
		update();
		super.draw(batch);
	}


}
