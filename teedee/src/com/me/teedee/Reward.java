package com.me.teedee;

/**
 * A class that represents the amount of money the enemies drop on kill
 * @author Daniel
 */
public class Reward {

	private Money reward;

	public Reward() {
		reward = new Money();
	}

	public Reward(int reward) {
		this.reward = new Money(reward);
	}

	public void setReward(int newReward) {
		this.reward.setMoney(newReward);
	}

	public int getReward() {
		return reward.getMoney();
	}
}
