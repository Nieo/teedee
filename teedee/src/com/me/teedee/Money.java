package com.me.teedee;

/**
 * Money class
 * @author Daniel
 */
public class Money {

	private int money;

	public Money() {
		money = 0;
	}

	public Money(int money) {
		this.money = money;
	}

	public void addMoney(int gainedMoney) {
		money += gainedMoney;		
	}

	public void removeMoney(int lostMoney) {
		money -= lostMoney;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
