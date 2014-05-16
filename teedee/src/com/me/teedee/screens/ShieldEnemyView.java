package com.me.teedee.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.enemies.ShieldEnemy;

public class ShieldEnemyView extends EnemyView {

	private ShieldEnemy se;
	private Sprite shieldSprite;
	private float origAlpha;

	public ShieldEnemyView(ShieldEnemy se) {
		super(se);
		this.se = se;
		shieldSprite = new Sprite(new Texture("img/unknown.png"));
		origAlpha = shieldSprite.getColor().a;
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
		}else if(shieldSprite.getColor().a <= 0f && !se.isShieldDown()){;
		shieldSprite.setAlpha(origAlpha);
		}
		shieldSprite.setX(this.getX());
		shieldSprite.setY(this.getY());

	}

}
