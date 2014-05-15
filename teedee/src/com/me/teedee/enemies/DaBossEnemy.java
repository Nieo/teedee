package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

public class DaBossEnemy extends AbstractEnemy {

	private final int id = 5;

	public DaBossEnemy(Path p){
		super(p, 120f, new Lives(50000), new Reward(1000));

	}

	@Override
	public int getId() {
		return id;
	}
}
