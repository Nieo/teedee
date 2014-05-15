package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;

public class SlowEnemy extends AbstractEnemy {
	
	private final int id = 3;

	public SlowEnemy(Path p) {
		super(p, 60f, new Lives(2000f), new Reward(65));
	}
	
	@Override
	public int getId(){
		return id;
	}

}
