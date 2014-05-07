package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;

public class SlowEnemy extends AbstractEnemy {
	
	private final int id = 3;
	
	public SlowEnemy(AbstractEnemy a) {
		super(a);
		updateSlowEnemy();
	}

	public SlowEnemy(Path p, Position pos) {
		super(p, 120f, new Lives(100), new Reward(50), pos);
		updateSlowEnemy();
	}

	public SlowEnemy(Path p) {
		super(p);
		updateSlowEnemy();
	}
	
	private void updateSlowEnemy(){
		this.setSpeed(this.getSpeed()*0.5f);
		int nr=(int) (this.getEnemyReward().getReward()*1.3);
		this.setReward( new Reward(nr));
		int nl= (int) (this.getLives().getLivesHealth()*2);
		this.setLives(new Lives(nl));
	}
	
	public int getId(){
		return id;
	}

}
