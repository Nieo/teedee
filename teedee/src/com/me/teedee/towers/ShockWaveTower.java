package com.me.teedee.towers;

import java.util.ArrayList;
import java.util.List;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class ShockWaveTower extends AbstractTower{
	public ShockWaveTower(Position pos, ArrayList<AbstractEnemy> enemies){
		price[0] = new Price(350);
		for(int i = 1; i < 5; i++){
			price[i] = new Price(100*i);
		}
			value = price[0].getPrice();
			currentLevel = 0;
			maxLevel = 5;
			for(int i = 0; i < 5; i++)
				attackSpeed[i] = 1;
			cooldown = attackSpeed[0];
			
			for(int i = 0; i < 5; i++)
				attackDamage[i] = 1 + i/2;
			
			status = new Status(1f, 0, 1);
			setPosition(pos);
			this.enemies = enemies;
			range = 150;
			id = 4;
	}
	
	@Override
	public void shoot(float delta){
		super.shoot(delta);
		

	}
	
	public List<AbstractEnemy> getNeighbourEnemies(){
		List<AbstractEnemy> neighbours = new ArrayList<AbstractEnemy>();
		AbstractEnemy tempEnemy = enemies.get(0);
		for(AbstractEnemy enemy : enemies){
//			if(distance(this.getTargetPosition(),tempEnemy.getPosition()) < ){
//				tempEnemy = enemy;
//			}
		}
		return neighbours;
	}

}
