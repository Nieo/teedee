package com.me.teedee;

/**
 * Player class
 * @author Daniel
 */

public class Player {

	private String name;
	private Money money;
	private Lives life;

	public Player() {
		name = "Player";
		money = new Money(1000);
		life = new Lives(100);
	}

	public Player(String name) {
		this.name = name;
		money = new Money(1000);
		life = new Lives(100);
	}

	public void addMoney(int gainedMoney) {
		money.addMoney(gainedMoney);		
	}

	public void removeMoney(int lostMoney) {
		money.removeMoney(lostMoney);		
	}

	public Money getMoney() {
		return money;
	}

	public int getMoneyInt() {
		return money.getMoney();
	}

	public void setMoney(int newMoney) {
		money.setMoney(newMoney);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public void takeDamage(int dmg) {
		life.lowerLives(dmg);
	}

	public Lives getLives() {
		return life;
	}
}
