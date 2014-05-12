package com.me.teedee.towers;

import java.util.ArrayList;
import java.util.Collections;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class MultiTower extends AbstractTower {

	private float cooldown;
	public MultiTower(Position pos, ArrayList<AbstractEnemy> enemies){
		price[0] = new Price(500);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(i * 100);

		value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 1.2f;
		cooldown = attackSpeed[0];

		for(int i = 0; i < 5; i++)
			attackDamage[i] = 1 ;

		status = new Status(0.5f,1,0);
		setPosition(pos);
		this.enemies = enemies;
		range = 400;
		id = 3;
	}
	@Override
	public void shoot(float delta){
		cooldown = cooldown - delta;
		
		if(cooldown <= 0) {
			
			cooldown = attackSpeed[currentLevel] + cooldown;
			target.clear();
			for(AbstractEnemy a: enemies){
				if(AbstractTower.distance(this.getPosition(), a.getPosition()) < this.getRange())
					target.add(a);
			}
			Collections.sort(target);
			Collections.reverse(target);
			for(int i = target.size()-1; i >= 0 ; i--){
				if(i < currentLevel+2){
					if(!target.get(i).takeDamage(attackDamage[currentLevel])) {
						kills++;
					}
				}else{
					target.remove(i);
				}
			if(!target.isEmpty()){
				isShooting = true;
			}
				
			}
			
		}else{
			isShooting = false;
		}
		
	}
}
