package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;
/**
 * A boss type enemy
 */
public class DaBossEnemy extends AbstractEnemy {

	private final int id = 5;

	public DaBossEnemy(Path p, int level){
		super(p, 120f, new Lives(Math.round(20000*(1+Math.pow(1.1, level)))), new Reward(5000));
	}

	@Override
	public int getId() {
		return id;
	}
}
