package com.me.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.enemies.ShieldEnemy;
/**
 *A special graphical class for the ShieldEnemy. Adds a bubble sprite for displaying the shield the ShieldEnemy has.
 */
public class ShieldEnemyView extends EnemyView {

	private ShieldEnemy se;
	private Sprite shieldSprite;

	public ShieldEnemyView(ShieldEnemy se) {
		super(se);
		this.se = se;
		shieldSprite = new Sprite(Assets.manager.get("img/shield.png", Texture.class));
		shieldSprite.setX(this.getPositionX()-20);
		shieldSprite.setY(this.getPositionY()-20);
	}

	@Override
	public void draw(Batch batch) {
		this.update();
		super.draw(batch);
		shieldSprite.draw(batch);
	}

	private void update() {
		if(se.isShieldDown()){
			shieldSprite.setAlpha(0f);
		} else {
			shieldSprite.setAlpha(se.getShield()/se.getShieldMax());
		}
		shieldSprite.setX(this.getX()-20);
		shieldSprite.setY(this.getY()-20);
	}
}
