package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RadiusImage extends Actor {
	private Sprite sprite;
	private Sprite red;
	private float radius;
	private boolean isRed = false;
	
	public RadiusImage(Texture texture) {
		this(new Sprite(texture));
	}
	
	public RadiusImage(Sprite sprite) {
		this.sprite = sprite;
		this.red = new Sprite(new Texture("img/radius200_red.png"));
		red.setAlpha(0);
		this.radius = 100;
	}
	
	public void setColorRed() {
		red.setAlpha(1);
		sprite.setAlpha(0);
		isRed = true;
	}
	
	public void setColorDefault() {
		red.setAlpha(0);
		sprite.setAlpha(1);
		isRed = false;
	}
	
	public Texture getTexture() {
		return sprite.getTexture();
	}
	
	public void draw(Batch batch) {
		sprite.draw(batch);
		red.draw(batch);
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
		sprite.setSize(radius*2, radius*2);
		red.setSize(radius*2, radius*2);
	}
	
	@Override
	public void setPosition(float x, float y) {
		sprite.setPosition(x - radius + 40, y - radius + 40);
		red.setPosition(sprite.getX(), sprite.getY());
	}
	
	public void showRadius() {
		setAlpha(1);
	}
	
	public void hideRadius() {
		setAlpha(0);
	}
	
	public void setAlpha(float alpha) {
		sprite.setAlpha(alpha);
	}
	
	public boolean isRed() {
		return isRed;
	}
}
