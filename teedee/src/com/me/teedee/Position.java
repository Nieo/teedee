package com.me.teedee;



public class Position {
	private float xCoordinate;
	private float yCoordinate;
	
	public Position(){
		xCoordinate = 0;
		yCoordinate = 0;
	}
	public Position(float x, float y){
		xCoordinate = x;
		yCoordinate = y;
	}
	public float getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public float getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
