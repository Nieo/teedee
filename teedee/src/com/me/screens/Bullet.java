package com.me.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.teedee.Position;
import com.me.teedee.towers.AbstractTower;
/**
 * The bullet class is a graphical representation of the shots or bullets the towers fire
 */
public class Bullet extends Sprite{
	private Position targetPosition;
	private Position startPosition;
	private Vector2 direction;
	private float speed;
	private boolean hasHitTarget = false;

	public Bullet(float startX, float startY, float targetX, float targetY, float speed, Texture texture){
		this(new Position(startX,startY),new Position (targetX,targetY), speed, texture);
	}

	public Bullet(Position targetPosition, float speed, AbstractTower t){
		super(Assets.manager.get("img/IceBullet.png", Texture.class));
		switch(t.getId()) {
		case 1:
			setRegion(Assets.manager.get("img/fireBullet.png", Texture.class));
			break;
		case 2:
			setTexture(Assets.manager.get("img/IceBullet.png", Texture.class));
			break;
		case 3:
			setTexture(Assets.manager.get("img/greenBullet.png", Texture.class));
			break;
		case 4:
			setTexture(Assets.manager.get("img/shockwaveBullet.png", Texture.class));
			setSize(40, 40);
			break;
		case 5:
			setTexture(Assets.manager.get("img/blackHoleBIG.png", Texture.class));
			setSize(40, 40);
			break;
		case 6:
			//TODO Laser beams looks awful
			setTexture(Assets.manager.get("img/laserBeam.png", Texture.class));
			setSize(34, 60);
			break;
		default:
			setTexture(Assets.manager.get("img/RedBullet.png", Texture.class));
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

	public void update(float delta){
		startPosition = new Position(this.getX(),this.getY());
		Position targetImageCenter = new Position(targetPosition.getX() + 30,
				targetPosition.getY() + 30);
		direction = getDirection(startPosition, targetImageCenter);
		this.setPosition(getX() + speed*direction.x*delta, getY() + speed*direction.y*delta);
		setRotation(direction.angle() + 90);
		if(getDistance(startPosition, targetImageCenter) <= speed*delta ){
			this.setAlpha(0);
			hasHitTarget = true;
		}
	}

	public boolean hasHitTarget(){
		return hasHitTarget;
	}

	@Override
	public void draw(Batch batch, float delta) {
		update(delta);
		super.draw(batch);
	}
}
