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
	//private float travelledDistance = 0;
	private boolean hasHitTarget = false;


	public Bullet(float startX, float startY, float targetX, float targetY, float speed, Texture texture){
		this(new Position(startX,startY),new Position (targetX,targetY), speed, texture);

	}

	public Bullet(Position targetPosition, float speed, AbstractTower t){
		super(new Texture("img/IceBullet.png"));
		switch(t.getId()) {
		case 1:
			setRegion(new Texture("img/fireBullet.png"));
			break;
		case 2:
			setTexture(new Texture("img/IceBullet.png"));
			break;
			//osv
		case 6:
			//TODO Laserbeams looks awful
			setTexture(new Texture("img/laserBeam.png"));
			break;
		default:
			setTexture(new Texture("img/RedBullet.png"));
		}
		this.startPosition = new Position(t.getPosition().getX() + 45 - getWidth()/2, t.getPosition().getY() + 40 - getHeight()/2);
		this.targetPosition = targetPosition;
		this.direction = getDirection(startPosition, targetPosition);
		setOrigin(getX() + getWidth()/2, getY() + getHeight()/2);
		setRotation(direction.angle()+90);
		this.speed = speed;
		this.setPosition(startPosition.getX(), startPosition.getY());
		
	}

	public Bullet(Position startPosition, Position targetPosition, float speed, Texture texture){
		super(new Sprite(texture));
		this.startPosition = startPosition;
		this.targetPosition = targetPosition;
		this.direction = getDirection(startPosition, targetPosition);
		this.speed = speed;
		this.setPosition(startPosition.getX(), startPosition.getY());
	}
	
	private Vector2 getDirection(Position startPosition, Position targetPosition){
		return new Vector2(targetPosition.getX() - startPosition.getX(),
							targetPosition.getY() - startPosition.getY()).nor();
	}
	
	private float getDistance(Position a, Position b){
		return (float)Math.sqrt((Math.pow(b.getX()-a.getX(), 2)) 
				+ Math.pow(b.getY()-b.getY(), 2));
	}

	public void update(){
		startPosition = new Position(this.getX(),this.getY());
		direction = getDirection(startPosition, targetPosition);
		this.setPosition(getX() + speed*direction.x, getY() + speed*direction.y);
		
		if(getDistance(startPosition,targetPosition) <= speed ){
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
