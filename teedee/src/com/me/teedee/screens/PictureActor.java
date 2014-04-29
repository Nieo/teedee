package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PictureActor extends Actor {
	private Sprite sprite;
	
	public PictureActor(Texture texture) {
		this(new Sprite(texture));
	}
	
	public PictureActor(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void setTexture(Texture texture) {
		sprite.setTexture(texture);
	}
	
	public Texture getTexture() {
		return sprite.getTexture();
	}
	
	public void draw(Batch batch) {
		sprite.draw(batch);
	}
}
