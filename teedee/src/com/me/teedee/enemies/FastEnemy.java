package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

/**
 *  An enemy type which is faster than the basic enemy unit
 */
public class FastEnemy extends AbstractEnemy {

	private final int id = 2;

	public FastEnemy(Path p, int level){
		super(p, 180f, new Lives(Math.round(700*(Math.pow(1.1, level)))), new Reward(65));
	}

	@Override
	public int getId(){
		return id;
	}
}
