package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * A image that represents information about
 * a tower for example
 * @author Daniel
 */
public class InfoImage {

	private Label towerName;
	private Label towerPrice;
	private Label description;
	private Table table;
	private Skin skin;
	private float alpha;

	public InfoImage() {
		this("Tower", 100);
	}

	public InfoImage(String name, int price) {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		towerName = new Label(name, skin);
		towerPrice = new Label("$" + price,skin);
		description = new Label("", skin);
		description.setWrap(true);
		setText(name, price);
		table = new Table();
		table.setWidth(150);
		table.setHeight(200);
		table.left().top();
		table.setBackground(new SpriteDrawable(new Sprite(Assets.manager.get("img/infoTable.png", Texture.class))));
		table.add(towerName).left().top().padTop(10).padLeft(10).row();
		table.add(towerPrice).left().padLeft(10).row();
		table.add(description).left().padTop(10).padLeft(10).width(130);
	}

	public void draw(Batch batch) {
		table.draw(batch, alpha);
	}

	public void setUpgradeCost(int cost) {
		String s =(cost < Integer.MAX_VALUE ? "$"+cost : "Max level");
		towerName.setText("Upgrade price");
		towerPrice.setText(s);
		description.setText("");
		table.setWidth(130);
		table.setHeight(70);
	}

	public void choseTower(int index) {
		table.setWidth(150);
		table.setHeight(200);
		switch(index) {
		case 1:
			setText("Amber Dragon",400);
			setDescription(1);
			break;
		case 2:
			setText("Ice Dragon", 400);
			setDescription(2);
			break;
		case 3:
			setText("Hydra", 700);
			setDescription(3);
			break;
		case 4:
			setText("Carmine Dragon", 450);
			setDescription(4);
			break;
		case 5:
			setText("Lucky Dragon", 1000);
			setDescription(5);
			break;
		case 6:
			setText("Blood Dragon", 300);
			setDescription(6);
			break;
		}
	}

	private void setDescription(int id) {
		switch(id) {
		case 1:
			description.setText("A basic tower with normal damage and attack speed");
			break;
		case 2:
			description.setText("A tower that freezes enemies close by and makes them walk slower");
			break;
		case 3:
			description.setText("A tower that can hit many enemies at once");
			break;
		case 4:
			description.setText("A tower that makes damage to the enemies around a hit target");
			break;
		case 5:
			description.setText("A tower that has a random damage and has a chance to stun enemies");
			break;
		case 6:
			description.setText("A tower that increases its damage by killing enemies");
			break;
		}
	}

	public void setPosition(float x, float y) {
		table.setPosition(x, y);
	}

	public void setText(String name, int price) {
		towerName.setText(name);
		towerPrice.setText("$" + price);
	}

	public void show() {
		alpha = 1;
	}

	public void hide() {
		alpha = 0;
	}

	public float getHeight() {
		return table.getHeight();
	}

	public float getWidth() {
		return table.getWidth();
	}

	public void dispose() {
		skin.dispose();
	}
}
