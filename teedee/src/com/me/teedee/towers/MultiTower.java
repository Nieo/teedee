package com.me.teedee.towers;

import java.util.ArrayList;
import java.util.Collections;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class MultiTower extends AbstractTower {

	private int updateCounter;
	public MultiTower(Position pos, ArrayList<AbstractEnemy> enemies){
		price[0] = new Price(500);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(i * 100);

		value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 1;

		for(int i = 0; i < 5; i++)
			attackDamage[i] = 1 ;

		status = new Status(0.5,1,0);
		setPosition(pos);
		this.enemies = enemies;
		range = 200;
		id = 3;
	}
	public void shoot(){
		if(updateCounter%(UPDATE_SPEED/attackSpeed[currentLevel]) == 0){
			ArrayList<AbstractEnemy> targets = new ArrayList<AbstractEnemy>();
			for(AbstractEnemy a: enemies){
				if(AbstractTower.distance(this.getPosition(), a.getPosition()) < this.getRange())
					targets.add(a);
			}
			Collections.sort(targets);
			int bullets = currentLevel+1;
			if(targets.size() < 3){
				bullets = targets.size();
			}
			for(int i = 0; i < bullets; i++){
				targets.get(i).takeDamage(attackDamage[currentLevel], status);
			}	
		}
		updateCounter++;
		

	}
}