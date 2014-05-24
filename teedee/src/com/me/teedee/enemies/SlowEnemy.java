package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

/**
 * Slow moving enemy with lots of health and immunity to slow effects 
 */
public class SlowEnemy extends AbstractEnemy {

	private final int id = 3;

	public SlowEnemy(Path p, int level) {
		super(p, 60f, new Lives(Math.round(2000*(Math.pow(1.1, level)))), new Reward(65));
	}

	@Override
	public int getId(){
		return id;
	}
}
