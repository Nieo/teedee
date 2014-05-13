package com.me.teedee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * A text label that floats up and displays
 * a message and disappears after.
 * @author Daniel
 */
public class Notification {
	private Table table;
	private Label label;
	private float y;
	private float alpha = 1;
	private boolean done = false;

	public Notification(String text, float x, float y) {
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		table = new Table();
		label = new Label(text, skin);
		label.setFontScale(1.4f);
		table.add(label);
		table.setPosition(x, y);
		this.y = y;
	}

	public void moveTable() {
		if(table.getY() < y + 20) {
			table.setPosition(table.getX(), table.getY()+0.5f);
			alpha -= 0.025;
		} else {
			done = true;
			alpha = 0;
		}
	}

	public void setPosition(float x, float y) {
		table.setPosition(x, y);
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void draw(Batch batch) {
		moveTable();
		table.draw(batch, alpha);
	}

	public boolean isDone() {
		return done;
	}
}
