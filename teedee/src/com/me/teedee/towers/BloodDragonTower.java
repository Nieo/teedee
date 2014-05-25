package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

/**
 * A class representing a tower which damage on enemies
 * increases with the number of enemies it has killed."
 */
public class BloodDragonTower extends AbstractTower{
	int killsCount = 0;
	int startAttackDamage = 40;
	int currentAttackDamage;

	public BloodDragonTower(Position pos, ArrayList<AbstractEnemy> enemies){
		price[0] = new Price(300);
		for(int i = 1; i < 5; i++){
			price[i] = new Price(200*i + price[0].getPrice());
		}
		sellValue = Math.round(0.8f*price[0].getPrice());
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 1;
		cooldown = attackSpeed[0];

		for(int i = 0; i < 5; i++)
			attackDamage[i] = 50 + i * 50;

		status = new Status(1f, 0, 1);
		setPosition(pos);
		this.enemies = enemies;
		range = 200;
		id = 6;
		name = "Blood Dragon";
	}

	@Override
	public void shoot(float delta){
		if(cooldown - delta <= 0){
			/**Instead of rewriting (duplicating many rows of code) from the AbstractTower class,
			 * we just keep track of the number of killed enemies.
			 **/
			if(getKills() > killsCount){
				killsCount++;
				attackDamage[currentLevel] += 10;
			}
		}
		super.shoot(delta);
	}

	@Override
	public Boolean upgrade() {
		if(currentLevel < maxLevel - 1) {
			currentLevel++;
			sellValue += price[currentLevel].getPrice();
			//Adding the previous attack damage and the amount of attack damage for an upgrade (50)
			attackDamage[currentLevel] = attackDamage[currentLevel -1] + 50;
			return true;
		}
		return false;
	}
}
