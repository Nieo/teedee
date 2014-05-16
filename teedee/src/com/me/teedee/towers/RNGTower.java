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
			price[i] = new Price(300 + i * 100);
		value = price[0].getPrice();

		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 4; i++)
			attackSpeed[i] = 0.8f - 0.02f*1;
		attackSpeed[4] = 0.7f;
		cooldown = attackSpeed[0];

		for(int i = 0; i < 5; i++)
			attackDamage[i] = 200 + 100 * i;

		status = new Status(2f,0,0.3f);
		setPosition(pos);
		this.enemies = enemies;
		range = 280;
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
				int dmg;
				if(rand > 0.85){
					dmg = attackDamage[currentLevel] * 5;
					target.get(0).addTowerStatus(this, new Status(status));
				}else if(rand > 0.60){
					dmg = attackDamage[currentLevel];
				}else {
					dmg = attackDamage[currentLevel] /2;
					if(rand < 0.10)
						target.get(0).addTowerStatus(this, new Status(0.05f , 0 , 0.15f));
				}
				if(!target.get(0).takeDamage(dmg))
					kills++;

			}

		} else {
			isShooting = false;
		}
	}
}
