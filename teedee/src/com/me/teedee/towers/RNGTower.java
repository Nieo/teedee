package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class RNGTower extends AbstractTower {
		
	public RNGTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		price[0] = new Price(1000);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(400 + i * 100);
		value = price[0].getPrice();
		
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 0.8f;
		cooldown = attackSpeed[0];
		
		for(int i = 0; i < 5; i++)
			attackDamage[i] = 120 + 80 * (i+1);

		status = new Status(2f,0,0.3f);
		setPosition(pos);
		this.enemies = enemies;
		range = 500;
		id = 5;
	}
	
	@Override
	public void shoot(float delta){
		cooldown = cooldown - delta;
		if(cooldown <= 0) {
			cooldown = attackSpeed[currentLevel] + cooldown;
			target.clear();
			for(int i = 0; i < enemies.size(); i++) {
				if(distance(position, enemies.get(i).getPosition()) < range && enemies.get(i).isAlive()) {
					if(target.isEmpty()) {
						target.add(enemies.get(i));  
					} else {
						if(enemies.get(i).getStepsTraveled() > target.get(0).getStepsTraveled()){
								target.clear();
								target.add(enemies.get(i));
						}
					}
				}
			}
			
			if(!target.isEmpty()) {
				isShooting = true;
				double rand = Math.random();
				if(rand > 0.85){
					if(!target.get(0).takeDamage(attackDamage[currentLevel]* 5)) {
						kills++;
					}
					target.get(0).addTowerStatus(this, new Status(status));
				}else if(rand > 0.60){
					if(!target.get(0).takeDamage(attackDamage[currentLevel])) {
						kills++;
					}
				}else {
					if(!target.get(0).takeDamage(attackDamage[currentLevel]/2)) {
						kills++;
					}
					target.get(0).addTowerStatus(this, new Status(0.05f , 0 , 0.15f));
				}

			}
			
		} else {
			isShooting = false;
		}
	}
}
