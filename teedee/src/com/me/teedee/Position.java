package com.me.teedee;

public class Position {

	private float xCoordinate;
	private float yCoordinate;

	public Position() {
		xCoordinate = 0;
		yCoordinate = 0;
	}
	@Override
	public String toString(){
		return "X: " + xCoordinate + " Y: " + yCoordinate;
	}
	public Position(float x, float y) {
		xCoordinate = x;
		yCoordinate = y;
	}

	public Position(Position pos) {
		this(pos.xCoordinate,pos.yCoordinate);
	}
	public float getX() {
		return xCoordinate;
	}

	public void setxCoordinate(float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public float getY() {
		return yCoordinate;
	}

	public void setyCoordinate(float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public boolean equals(Position position){
		return this.getX() == position.getX() && this.getY() == position.getY();
	}
}
