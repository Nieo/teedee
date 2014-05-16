package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

// 50% faster than a regular enemy
public class FastEnemy extends AbstractEnemy {

	private final int id = 2;
	
	public FastEnemy(Path p, int level){
		super(p, 180f, new Lives(700*(1+0.1f*level)), new Reward(65));
		
	}

	@Override
	public int getId(){
		return id;
	}

}
