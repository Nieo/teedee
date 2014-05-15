package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

// FIXME 50% faster than a regular enemy
public class FastEnemy extends AbstractEnemy {

	private final int id = 2;

	public FastEnemy(Path p){
		super(p, 240f, new Lives(700), new Reward(65));

	}

	@Override
	public int getId(){
		return id;
	}

}
