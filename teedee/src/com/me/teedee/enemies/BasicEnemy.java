package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

/**
 * A most basic enemy, extends abstract enemy
 * @author Fridgeridge
 *
 */
public class BasicEnemy extends AbstractEnemy {

	private final int id = 1;

	public BasicEnemy(Path p, int level) {
		super(p, 120f, new Lives(700*(1+0.1f*level)), new Reward(50));
	}

	@Override
	public int getId() {
		return id;
	}
}
