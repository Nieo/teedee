package com.me.teedee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
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
		super(new Texture("img/RedBullet.png"));
		switch(t.getId()) {
		case 1:
			setTexture(new Texture("img/RedBullet.png"));
			break;
		case 2:
			setTexture(new Texture("img/BlueBullet.png"));
			break;
		default:
			setTexture(new Texture("img/RedBullet.png"));
		}
		startPosition = new Position(t.getPosition().getX() + 45, t.getPosition().getY() + 40);
		targetPosition = new Position(targetX, targetY);
		this.direction = new Vector2(targetPosition.getX() - startPosition.getX(),
				targetPosition.getY() - startPosition.getY()).nor();
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
