package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class IceTower extends AbstractTower {
	ArrayList<Status> statusLevels = new ArrayList<Status>();
	
	public IceTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		
		statusLevels.add(new Status(0.50f,50,1.2f));
		statusLevels.add(new Status(0.40f,75,1.7f));
		statusLevels.add(new Status(0.35f,100,2.5f));
		
		price[0] = new Price(400);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(100*i);

		value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 3;
		for(int i = 0; i < maxLevel; i++)
			attackSpeed[i] = 0.7f;
		cooldown = attackSpeed[0];
		
		for(int i = 0; i < maxLevel; i++)
			attackDamage[i] = 20 + 10*i;

		status = statusLevels.get(0);
		setPosition(pos);
		this.enemies = enemies;
		range = 150;
		id = 2;



	}


	@Override
	public Boolean upgrade(){
		if(super.upgrade()){
			status = statusLevels.get(currentLevel);
			return true;
		}
		return false;
	}
}
