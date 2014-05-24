package com.me.teedee;

/**
 * A class that represents the player of the Tower Defense game
 * @author Daniel
 */
public class Player {

	private String name;
	private Money money;
	private Lives life;

	public Player() {
		name = "Player";
		money = new Money(900);
		life = new Lives(10);
	}

	public Player(String name) {
		this.name = name;
		money = new Money(900);
		life = new Lives(10);
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
