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

	public BasicEnemy(Path p) {
		super(p, 120f, new Lives(1000), new Reward(50));
	}

	@Override
	public int getId() {
		return id;
	}

}
