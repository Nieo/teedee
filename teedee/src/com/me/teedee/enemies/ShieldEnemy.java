package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Reward;

/**
 * An enemy with a damage-absorbing shield. The shield regenrates over time. 
 */
public class ShieldEnemy extends AbstractEnemy {

	private final int id = 4;

	private float shieldLimit;
	private float shield;
	private float regen;

	private boolean shieldDown = false;

	public ShieldEnemy(Path p, int level){
		super(p, 120f, new Lives(Math.round(500*(Math.pow(1.1, level)))),new Reward(65));
		shield = Math.round(1000*(Math.pow(1.1, level)));
		shieldLimit = Math.round(1000*(Math.pow(1.1, level)));

		regen = 100f;
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
		} else {
			takeShieldDamage(damage);
			return false;
		}
	}

	private boolean takeShieldDamage(float damage) {
		if(shield <= damage){
			shieldDown = true;
			shield = 0;
			return true;
		} else {
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

	@Override
	public int getId(){
		return id;
	}
	
	public float getShieldMax() {
		return shieldLimit;
	}
	
	public float getShield() {
		return shield;
	}
}
