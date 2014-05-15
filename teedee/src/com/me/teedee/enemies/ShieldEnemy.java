package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

//An enemy with a damage-absorbing shield 
public class ShieldEnemy extends AbstractEnemy {

	private final int id = 4;

	private float shieldLimit;
	private float shield;
	private float regen;

	private boolean shieldDown = false;

	public ShieldEnemy(Path p){
		super(p, 120f, new Lives(500),new Reward(65));
		this.updateShieldEnemy();
	}

	@Override
	public void move(float delta){
		super.move(delta);
		this.regenShield(delta);	
	}

	@Override
	public boolean takeDamage(int damage) {
		if(this.takeShieldDamage(damage) || shieldDown){
			return super.takeDamage(damage);
		}else{
			takeShieldDamage(damage);
			return false;
		}
	}

	private boolean takeShieldDamage(float damage) {
		if(shield <= damage){
			shieldDown = true;
			shield = 0;
			return true;
		}else{
			shield -= damage;
			return false;
		}
	}

	private void regenShield(float delta){
		if(shield < shieldLimit){
			shield += regen/(1/delta);
		}
		//Checks whether the shield has regened enough to be considered up.
		if(shieldDown && shield>100f){
			shieldDown = false;
		}	
	}

	public boolean isShieldDown(){
		return shieldDown;
	}

	private void updateShieldEnemy(){
		shield = 1000f;
		shieldLimit = 1000f;

		regen = 100f;

		this.setLives(new Lives(getLives().getMaxLives()*0.5f));
		int nr=(int) (this.getEnemyReward().getReward()*1.3);
		this.setReward( new Reward(nr));
	}

	@Override
	public int getId(){
		return id;
	}

}
