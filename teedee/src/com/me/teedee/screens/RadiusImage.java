package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RadiusImage extends Actor {
	private Sprite sprite;
	private float radius;
	
	public RadiusImage(Texture texture) {
		this(new Sprite(texture));
	}
	
	public RadiusImage(Sprite sprite) {
		this.sprite = sprite;
		this.radius = 100;
	}
	
	public Texture getTexture() {
		return sprite.getTexture();
	}
	
	public void draw(Batch batch) {
		sprite.draw(batch);
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
		sprite.setSize(radius*2, radius*2);
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x - radius + 40, y - radius + 40);
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
}
