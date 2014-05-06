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

	public SlowEnemy(Path p, float sp, Lives l, Reward r, Position pos) {
		super(p, sp, l, r, pos);
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
