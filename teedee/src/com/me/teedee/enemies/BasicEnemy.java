package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

/**
 * A most basic enemy, extends AbstractEnemy
 * @author Fridgeridge
 *
 */
public class BasicEnemy extends AbstractEnemy {

	private final int id = 1;

	public BasicEnemy(Path p, int level) {
		super(p, 120f, new Lives(Math.round(700*(Math.pow(1.1, level)))), new Reward(50));
		System.out.println(super.getLives().getMaxLives());
	}

	@Override
	public int getId() {
		return id;
	}
}
