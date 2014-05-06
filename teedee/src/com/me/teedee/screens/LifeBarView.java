package com.me.teedee.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.teedee.AbstractEnemy;

public class LifeBarView extends Sprite {
	private AbstractEnemy ae;
	private
	
	@Override
	public void draw(Batch batch) {
		// TODO Auto-generated method stub
		super.draw(batch);
	}

	public LifeBarView(AbstractEnemy e){
		ae=e;
	}
	
	
	

}
