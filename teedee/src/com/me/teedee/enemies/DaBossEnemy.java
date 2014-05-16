package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

public class DaBossEnemy extends AbstractEnemy {

	private final int id = 5;

	public DaBossEnemy(Path p, int level){
		super(p, 120f, new Lives(20000*(1+0.1f*level)), new Reward(5000));
	}

	@Override
	public int getId() {
		return id;
	}
}
