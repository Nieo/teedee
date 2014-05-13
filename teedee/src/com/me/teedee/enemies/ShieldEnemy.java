package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;
//An enemy with a damage-absorbing shield 
public class ShieldEnemy extends AbstractEnemy {
		
	private final int id = 4;
	
	private float shieldLimit;
	private float shield;
	private float regen;
	
	public ShieldEnemy(Path p){
		super(p);
		this.updateShieldEnemy();
	}
	
 	public ShieldEnemy(ShieldEnemy a) {
		super(a);
		this.updateShieldEnemy();
	}
	
 	public ShieldEnemy(Path p, Position pos) {
 		super(p, 120f,new Lives(100),new Reward(50),pos);
 		this.updateShieldEnemy();
 	}
	
 	@Override
 	public void move(float delta){
 	super.move(delta);
 	this.regenShield(delta);	
 	}
 	
 	@Override
 	public boolean takeDamage(int damage) {
 		if(this.takeShieldDamage(damage)){
 			return super.takeDamage(damage);
 		}else{
 			takeShieldDamage(damage);
 			return false;
 		}
 	}
	
 	
	
	private boolean takeShieldDamage(float damage) {
		if(shield <= damage){
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
