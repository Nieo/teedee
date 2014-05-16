package com.me.teedee.enemies;

import java.util.Iterator;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;
import com.me.teedee.Status;
	
	/*
	 * Slow moving enemy with lots of health and immunity to slow effects 
	 */


public class SlowEnemy extends AbstractEnemy {

	private final int id = 3;

	public SlowEnemy(Path p, int level) {
		super(p, 60f, new Lives(2000*(1+0.1f*level)), new Reward(65));
	}

	@Override
	public int getId(){
		return id;
	}
	
	
}
