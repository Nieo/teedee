package com.me.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 *A class that graphically show the range of the towers.
 */
public class RadiusImage extends Actor {
	private Sprite black;
	private Sprite red;
	private float radius;
	private boolean isRed = false;

	public RadiusImage(Texture texture) {
		this(new Sprite(texture));
	}

	public RadiusImage(Sprite sprite) {
		this.black = sprite;
		this.red = new Sprite(Assets.manager.get("img/radius200_red.png", Texture.class));
		red.setAlpha(0);
		this.radius = 100;
	}

	public void setColorRed() {
		red.setAlpha(1);
		black.setAlpha(0);
		isRed = true;
	}

	public void setColorDefault() {
		red.setAlpha(0);
		black.setAlpha(1);
		isRed = false;
	}

	public Texture getTexture() {
		return black.getTexture();
	}

	public void draw(Batch batch) {
		black.draw(batch);
		red.draw(batch);
	}

	public void setRadius(float radius) {
		this.radius = radius;
		black.setSize(radius*2, radius*2);
		red.setSize(radius*2, radius*2);
	}

	@Override
	public void setPosition(float x, float y) {
		black.setPosition(x - radius + 40, y - radius + 40);
		red.setPosition(black.getX(), black.getY());
	}

	public void showRadius() {
		if(isRed) {
			red.setAlpha(1);
			black.setAlpha(0);
		} else {
			black.setAlpha(1);
			red.setAlpha(0);
		}
	}

	public void hideRadius() {
		black.setAlpha(0);
		red.setAlpha(0);
	}

	public boolean isRed() {
		return isRed;
	}
}
