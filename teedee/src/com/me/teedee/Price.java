package com.me.teedee;

/**
 * Class that represent the cost of towers
 * @author Daniel
 */
public class Price {

	private Money price;

	public Price() {
		price = new Money();
	}

	public Price(int price) {
		this.price = new Money(price);
	}

	public int getPrice() {
		return price.getMoney();
	}

	public void setPrice(int newPrice) {
		price.setMoney(newPrice);
	}
}